FROM adoptopenjdk/openjdk15:alpine as cautious-engine_http4k-random_build

WORKDIR /app
COPY . ./

RUN ./gradlew build

FROM adoptopenjdk/openjdk15:alpine-jre as cautious-engine_http4k-random_release

WORKDIR /app
COPY --from=cautious-engine_http4k-random_build /app/build/libs/http4k_postgres-1.0-SNAPSHOT-all.jar /app/cautiousengine.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-jar", "/app/cautiousengine.jar"]
