# cautious-engine
A work in progress comparison of web frameworks for Kotlin microservices.

The intent of this project is to provide a relative comparison of the
performance of basic microservices built using different technologies. It is 
meant to be an expanded take on [Practical Performance Comparison of Spring Boot, 
Micronaut 1.3, Micronaut 2.0](https://micronaut.io/blog/2020-04-28-performance-comparison-spring-boot-micronaut.html)

### WIP: Short Term vs. Long Term
- The intial goal of the project is to support a generic GET endpoint that 
returns random data generated by JavaFaker.
- Expand to support CRUD operations backed by a Postgres datastore. The goal 
will be to compare performance of a more realistic service, as well as sense 
of how Spring Data JPA, Micronaut Data JPA, and Exposed stack up in a
realitive sense. This could be expanded to include other ORM/persistence
frameworks.
- Potentially do something similar with a NoSQL DB, most likely Mongo.

### Comparisons 
- `spring-boot`: Spring Boot 2.x
- `micronaut`: Micronaut 2.x
- `ktor`: Ktor, Kodein (or Koin), Exposed

### Considerations for Future Additions
Longer term thoughts for additions. 
- http4k
- Quarkus
- Javalin
- Jooby
- Jetty vs Netty vs Tomcat vs ?
- JVM comparisons (AdoptOpenJDK, Corretto, GraalVM, etc)
- Jackson vs. GSON vs. kotlinx.serialization vs ?
- Dependency Injection alternatives
- other languages? (Go, Rust, etc)

## Sources
[Practical Performance Comparison of Spring Boot, Micronaut 1.3, Micronaut 2.0](https://micronaut.io/blog/2020-04-28-performance-comparison-spring-boot-micronaut.html)

[Micronaut Comparisons source on GitHub](https://github.com/micronaut-projects/micronaut-comparisons)

[Micronaut vs. Quarkus vs. Spring Boot Performance on JDK 14](https://micronaut.io/blog/2020-04-07-micronaut-vs-quarkus-vs-spring-boot-performance-jdk-14.html)

[Kotlin for Server Side](https://kotlinlang.org/lp/server-side/)

[kotlin.link](https://kotlin.link/)
