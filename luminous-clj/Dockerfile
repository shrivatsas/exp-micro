FROM openjdk:8-alpine

COPY target/uberjar/luminous-clj.jar /luminous-clj/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/luminous-clj/app.jar"]
