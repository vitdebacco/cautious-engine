FROM adoptopenjdk/openjdk15:alpine as cautious-engine_jooby-random_build

WORKDIR /app
COPY . ./

RUN ./gradlew build

FROM adoptopenjdk/openjdk15:alpine-jre as cautious-engine_jooby-random_release

WORKDIR /app
COPY --from=cautious-engine_jooby-random_build /app/build/libs/jooby_postgres-1.0-SNAPSHOT-all.jar /app/cautiousengine.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx512m", "-jar", "/app/cautiousengine.jar"]
