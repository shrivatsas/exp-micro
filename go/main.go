package main

import (
	// "html/template"
	"context"
	"flag"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"os"
	"os/signal"
	"time"
	// "sync"

	"github.com/gin-gonic/gin"
	"github.com/hashicorp/consul/api"
	"github.com/hashicorp/consul/connect"
	gintrace "go.opentelemetry.io/contrib/instrumentation/github.com/gin-gonic/gin"
	otelglobal "go.opentelemetry.io/otel/api/global"
	// oteltrace "go.opentelemetry.io/otel/api/trace"
	zipkin "go.opentelemetry.io/otel/exporters/trace/zipkin"
	// "go.opentelemetry.io/otel/label"
	sdktrace "go.opentelemetry.io/otel/sdk/trace"
)

var tracer = otelglobal.Tracer("gin-server")

func initTracer(url string) {
	err := zipkin.InstallNewPipeline(
		url,
		"zipkin-test",
		zipkin.WithSDK(&sdktrace.Config{DefaultSampler: sdktrace.AlwaysSample()}),
	)
	if err != nil {
		log.Fatal(err)
	}
}

func fetchFacts(ch chan string) {
	resp, _ := http.Get("http://localhost:3001/random")
	body, _ := ioutil.ReadAll(resp.Body)
	defer resp.Body.Close()
	ch <- string(body)
}

func fetchImage(ch chan []byte) {
	resp, _ := http.Get("http://localhost:3002/random")
	body, _ := ioutil.ReadAll(resp.Body)
	defer resp.Body.Close()
	ch <- body
	fmt.Println("Found image")
}

func fetchDatetime(ch chan string) {
	resp, _ := http.Get("http://localhost:3003/random")
	body, _ := ioutil.ReadAll(resp.Body)
	defer resp.Body.Close()
	ch <- string(body)
}

func main() {
	url := flag.String("zipkin", "http://localhost:9411/api/v2/spans", "zipkin url")

	client, _ := api.NewClient(api.DefaultConfig())
	svc, _ := connect.NewService("go", client)
	defer svc.Close()

	flag.Parse()

	initTracer(*url)
	r := gin.Default()
	r.Use(gintrace.Middleware("my-server"))
	r.GET("/ping", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "pong",
		})
	})
	r.GET("/random", func(c *gin.Context) {
		ich := make(chan []byte)
		fch := make(chan string)
		dch := make(chan string)
		go fetchDatetime(dch)
		go fetchFacts(fch)
		go fetchImage(ich)
		facts := <-fch
		dt := <-dch
		// img := <-ich

		close(ich)
		close(fch)
		close(dch)
		c.JSON(200, gin.H{
			"facts":    facts,
			"datetime": dt,
		})
	})

	srv := &http.Server{
		Addr:    ":8000",
		Handler: r,
	}

	go func() {
		// service connections
		if err := srv.ListenAndServe(); err != nil {
			log.Printf("listen: %s\n", err)
		}
	}()

	// Wait for interrupt signal to gracefully shutdown the server with
	// a timeout of 5 seconds.
	quit := make(chan os.Signal)
	signal.Notify(quit, os.Interrupt)
	<-quit
	log.Println("Shutdown Server ...")

	ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
	defer cancel()
	if err := srv.Shutdown(ctx); err != nil {
		log.Fatal("Server Shutdown:", err)
	}
	log.Println("Server exiting")
}
