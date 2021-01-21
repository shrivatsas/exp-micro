## Tracing using OpenTelemetry

Opentelemetry is the interface layer, which can then be enabled with open source or proprietary backends.

Zipkin / Jaeger are 2 available open source backends.
Jaeger UI : http://localhost:16686/
Zipkin UI : http://localhost:9411/zipkin/

Quarkus
    Uses Jaeger as a configuration

Micronaut
    Uses Zipkin library

ExpressJS
    https://github.com/open-telemetry/opentelemetry-js/blob/master/getting-started/README.md
    Uses Zipkin

FastAPI
    Uses Zipkin library

Gin
    Uses Zipkin library

Helidon
    Uses Zipkin library

Luminous
    TODO

Phoenix
    TODO
