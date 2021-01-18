# micro-exp

A place to play with the latest microservices fads.

## Phase 1: The setup

Setup basic microservices across languages, each doing one thing - making an api call to 3rd party service, managing some business logic, responding to user input. [more](docs/Phase1.md)

## Phase 2: Instrument

Microservices have a lot of moving parts, one way to keeping track of those is using logs and tracing. Logs involve aggregating messages explicitly written out by the system under observation. Tracing involves implicitly keeping track of control flow and parameters such as timing, variable values. Tracing data could be sampled or made available for all invocations. [more](docs/Phase2.md)

## Phase 3: Monitor  

Monitoring services is a operations imperative; tracking uptimes, latency, errors and other custom metrics. [more](docs/Phase3.md)

## Phase 4: Scale

One of the promises of microservices is horizontal scaling, ie. increase number of instances proportionate to demand. This promise demands location transparency, online switch/failovers and backups. One way to achieve such properties is by using an independant tool for 'service discovery, configuration and segmentation'. [more](docs/Phase4.md)

## Phase 5: Clean, Secure Interfaces

A microservice platform can end up presenting a wide interface to consumers. Badly designed interface can end up blocking change management, be insecure, and hard to maintain. [more](docs/Phase5.md)

## Phase 6: Higher tolerance

An end-to-end REST API based synchronous system can be tightly coupled leading to cascading failures. The uptime costs in such a system can become too high. One way to design a more tolerant system is to reduce coupling using asynchronous and messaging system, the communication semantics change too. [more](docs/Phase6.md)

## Tools and Usage

Starting with Docker, Docker-Compose, language build systems (lein, maven, mix, ..), Github actions, DroneCI, etc. we explore lots of tools. This section captures a few advanced usages / syntax found useful. [more](docs/tools-advanced.md)