FROM adoptopenjdk/openjdk14:jdk-14_36-alpine-slim as build

WORKDIR /app
COPY . ./

RUN ./gradlew build

FROM adoptopenjdk/openjdk14:jre-14_36-alpine as release
EXPOSE 8080

WORKDIR /app

COPY --from=build /app/build/libs/http4k-exposed-1.0-SNAPSHOT-all.jar /app
COPY --from=build /app/build/resources/main/* /app/src/main/resources/

CMD java -jar /app/http4k-exposed-1.0-SNAPSHOT-all.jar
