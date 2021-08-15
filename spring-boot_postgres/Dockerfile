FROM adoptopenjdk/openjdk15:alpine as cautious-engine-springboot_random-build

WORKDIR /app
COPY . ./

RUN ./gradlew build

FROM adoptopenjdk/openjdk15:alpine-jre as cautious-engine-springboot_random-release

WORKDIR /app
COPY --from=cautious-engine-springboot_random-build /app/build/resources/main/application.properties /app/application.properties
COPY --from=cautious-engine-springboot_random-build /app/build/libs/cautiousengine-0.0.1-SNAPSHOT.jar /app/cautiousengine.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx512m", "-jar", "/app/cautiousengine.jar"]
