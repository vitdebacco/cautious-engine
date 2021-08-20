FROM adoptopenjdk/openjdk15:alpine as cautious-engine_quarkus-random_build

WORKDIR /app
COPY . ./

RUN ./gradlew build

RUN ls -ahl build
RUN ls -ahl build/quarkus-app

FROM adoptopenjdk/openjdk15:alpine-jre as cautious-engine_quarkus-random_release

WORKDIR /app

COPY --from=cautious-engine_quarkus-random_build /app/build/quarkus-app/lib/ /app/lib/
COPY --from=cautious-engine_quarkus-random_build /app/build/quarkus-app/*.jar /app/
COPY --from=cautious-engine_quarkus-random_build /app/build/quarkus-app/app/ /app/app/
COPY --from=cautious-engine_quarkus-random_build /app/build/quarkus-app/quarkus/ /app/quarkus/

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx128m", "-jar", "/app/quarkus-run.jar"]
