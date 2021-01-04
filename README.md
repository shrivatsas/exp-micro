# micro-exp

A place to play with the latest microservices fads.

## Phase 1  
    Write up some basic microservices across languages / fws, with REST endpoint  
    NodeJS  (3001) - express - Generate / Fetch random single sentences
    Python  (3002) - fastAPI - Generate / Fetch random images
    clojure (3003) - luminous - Fetch current time in a random time zone  
    golang  (8000) - gin - Combine results and respond    
    Elixir  (9000) - phoenix - chat service to fetch images / text / times
    java (3004) - quarkus - Fetch random stock ticks
    java (3005) - helidon-se - Fetch random cricket scores
    java (3006) - micronaut - Fetch

## Phase 2  
    Add OpenTelemetry, Zipkin / Jaeger backend
    https://github.com/open-telemetry/opentelemetry-js/blob/master/getting-started/README.md
    Jaeger UI : http://localhost:16686/
    Zipkin UI : http://localhost:9411/zipkin/

## Phase 3  
    Monitoring using Prometheus
    Prometheus UI : http://localhost:9090/graph
    Add CircuitBreaker and Client side load balancing

## Phase 4  
    Consul UI : http://localhost:8500/ui/
    Service Discovery, Configuration and Segmentation.
    Agents as Clients, Servers and Proxies.

## Phase 5  
    API Gateway : Ambassador
    Ambassador UI : http://localhost:8080
    https://www.getambassador.io/docs/latest/topics/install/

    Add gRPC

## Phase 6  
    Setup NATS
    Add Message Broker        
