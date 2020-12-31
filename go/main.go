package main

import (
	// "html/template"
	"flag"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	// "sync"

	"github.com/gin-gonic/gin"
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
	r.Run(":8000") // listen and serve on 0.0.0.0:8000 (for windows "localhost:8000")
}
