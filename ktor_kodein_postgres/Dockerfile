FROM adoptopenjdk/openjdk15:alpine as cautious-engine-http4k_random-build

WORKDIR /app
COPY . ./

RUN ./gradlew build

FROM adoptopenjdk/openjdk15:alpine-jre as cautious-engine-http4k_random-release

WORKDIR /app
COPY --from=cautious-engine-http4k_random-build /app/build/libs/http4k_postgres-1.0-SNAPSHOT-all.jar /app/cautiousengine.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-jar", "/app/cautiousengine.jar"]
