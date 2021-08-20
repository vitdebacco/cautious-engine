FROM adoptopenjdk/openjdk15:alpine as cautious-engine_micronaut-random_build

WORKDIR /app
COPY . ./

RUN ./gradlew build

FROM adoptopenjdk/openjdk15:alpine-jre as cautious-engine_micronaut-random_release

WORKDIR /app
COPY --from=cautious-engine_micronaut-random_build /app/build/resources/main/logback.xml /app/logback.xml
COPY --from=cautious-engine_micronaut-random_build /app/build/resources/main/application.yml /app/application.yml
COPY --from=cautious-engine_micronaut-random_build /app/build/libs/micronaut_postgres-0.1-all.jar /app/cautiousengine.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-jar", "/app/cautiousengine.jar"]
