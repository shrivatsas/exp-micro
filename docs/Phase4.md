## Service Discovery, Configuration and Segmentation

Consul UI : http://localhost:8500/ui/
Agents as Clients, Servers and Proxies.

(De)Register via curl
[Register](../consul/register_consul.sh)
[Deregister](../consul/deregister_consul.sh)

(De)Register on service startup
Quarkus
    Need to make explicit http calls on Startup and Shutdown events
    Using java Consul library

Micronaut
    Natively supports Service discovery extensions
    Only changes in properties / yml are sufficient

ExpressJS
    Need to make explicit http calls on app init, and handle shutdown interrupts
    Using javascript Consul library

FastAPI
    Need to make explicit http calls on Startup and Shutdown events
    Using python Consul library

Gin
    Need to make explicit http calls on Startup and Shutdown events
    Using Hashicorp official library

Helidon
    https://dzone.com/articles/not-only-spring-boot-a-review-of-alternatives

Luminous
    https://github.com/hadielmougy/clj-consul-catalog

Phoenix
    No libraries, unclear at this point.
    Fall back to simple HTTP on startup / shutdown events