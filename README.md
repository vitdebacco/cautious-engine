# cautious-engine
A comparison of Spring Boot, Micronaut 2.x, Ktor, and http4k for microservices with Kotlin.

The intent of this project is to provide a relative comparison of the performance of basic REST APIs built using different technologies. It is meant
to be an expanded take on [Practical Performance Comparison of Spring Boot, Micronaut 1.3, Micronaut 2.0](https://micronaut.io/blog/2020-04-28-performance-comparison-spring-boot-micronaut.html)

### Comparisons 
- `spring-boot`: Spring Boot 2.x
- `micronaut`: Micronaut 2.x
- `ktor`: Ktor, Kodein (or Koin), Exposed ORM
- `http4k-exposed`: http4k, Exposed ORM, no DI
- `http4k-kodein-exposed`: http4k, Kodein DI, Exposed ORM 

### Considerations for Future Additions
Longer term thoughts for additions. 
- Quarkus
- Javalin
- Jooby for Kotlin
- Jackson vs. gson vs. kotlinx.serialization vs ?
- Dependency Injection alternatives
- other languages? (Go, Rust, etc)

## Sources
[Practical Performance Comparison of Spring Boot, Micronaut 1.3, Micronaut 2.0](https://micronaut.io/blog/2020-04-28-performance-comparison-spring-boot-micronaut.html)

[Micronaut Comparisons source on GitHub](https://github.com/micronaut-projects/micronaut-comparisons)

[Micronaut vs. Quarkus vs. Spring Boot Performance on JDK 14](https://micronaut.io/blog/2020-04-07-micronaut-vs-quarkus-vs-spring-boot-performance-jdk-14.html)

[Kotlin for Server Side](https://kotlinlang.org/lp/server-side/)

[kotlin.link](https://kotlin.link/)
