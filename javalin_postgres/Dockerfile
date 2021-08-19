FROM adoptopenjdk/openjdk15:alpine as cautious-engine_javalin-random_build

WORKDIR /app
COPY . ./

RUN ./gradlew build

FROM adoptopenjdk/openjdk15:alpine-jre as cautious-engine_javalin-random_release

WORKDIR /app
COPY --from=cautious-engine_javalin-random_build /app/build/libs/javalin_postgres-1.0-SNAPSHOT-all.jar /app/cautiousengine.jar
COPY --from=cautious-engine_javalin-random_build /app/build/resources/main/logback.xml /app/logback.xml

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-jar", "/app/cautiousengine.jar"]
