# micro-exp

A place to play with the latest microservices fads.

## Phase 1  
    Write up some basic microservices across languages / fws, with REST endpoint  
    NodeJS  (3001) - express - Generate / Fetch random single sentences
    Python  (3002) - FastAPI - Generate / Fetch random images
    clojure (3003) - luminous - Fetch current time in a random time zone  
    golang  - gin - Combine results and respond    
    Elixir  - [Phoenix](/elixir/hello) - 
    java    - quarkus, helidon-se, helidon-mp, micronaut

## Phase 2  
    Add OpenTelemetry, Zipkin backend
    https://github.com/open-telemetry/opentelemetry-js/blob/master/getting-started/README.md
    docker run -d -p 9411:9411 openzipkin/zipkin
    docker run -d -p5775:5775/udp -p6831:6831/udp -p6832:6832/udp -p5778:5778 -p16686:16686 -p14268:14268 jaegertracing/all-in-one:latest

## Phase 3  
    Add Dockerfile

## Phase 4  
    Add ServiceDiscovery with Consul  

## Phase 5  
    Add gRPC

## Phase 6  
    Add Message Broker        
