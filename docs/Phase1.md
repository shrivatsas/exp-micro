
Setup basic microservices across languages, each doing one thing - making an api call to 3rd party service, managing some business logic, responding to user input.

The services will initially use REST APIs for organization. Look at the dependencies from various perspectives,
1. Configurability
2. Performance
3. Ease of setup and use
4. Feature support

The services,
1. [ExpressJS](http://expressjs.com/)
   1. Fetch random single sentences
   2. Port: 3001

2. [FastAPI](https://fastapi.tiangolo.com/)
   1. Fetch random images
   2. Port: 3002

3. [Luminous](https://luminusweb.com/)
   1. Fetch current time in a random time zone  
   2. Port: 3003

4. [Phoenix](https://www.phoenixframework.org/)
   1. Chat service to fetch images / text / times
   2. Port: 9000

5. [Quarkus](https://quarkus.io/)
   1. Fetch random stock ticks
   2. Port: 3004

6. [Helidon SE](https://helidon.io/)
   1. Fetch random quotes
   2. Port: 3005

7. [Micronaut](https://micronaut.io/)
   1. Generate a random color
   2. Port: 3006

8. [Gin](https://gin-gonic.com/)
   1. Combine results and respond
   2. Port: 8000