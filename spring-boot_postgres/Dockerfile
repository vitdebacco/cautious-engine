FROM adoptopenjdk/openjdk15:alpine as cautious-engine_springboot-random_build

WORKDIR /app
COPY . ./

RUN ./gradlew build

FROM adoptopenjdk/openjdk15:alpine-jre as cautious-engine_springboot-random_release

WORKDIR /app
COPY --from=cautious-engine_springboot-random_build /app/build/resources/main/application.properties /app/application.properties
COPY --from=cautious-engine_springboot-random_build /app/build/libs/cautiousengine-0.0.1-SNAPSHOT.jar /app/cautiousengine.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx512m", "-jar", "/app/cautiousengine.jar"]
