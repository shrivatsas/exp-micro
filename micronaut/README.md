## Feature http-client documentation

- [Micronaut Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

## Setup graalVM native image build

    gu install native-image

    native-image \
    --no-server \
    --no-fallback \
    -cp build/libs/recipes-micronaut-*-all.jar \
    de.tbuss.recipesmicronaut.Application

## To run using mvn

    mvn mn:run