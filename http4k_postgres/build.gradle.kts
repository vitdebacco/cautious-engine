plugins {
    application
    kotlin("jvm") version "1.4.21"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("org.jmailen.kotlinter") version "3.3.0"
}

val http4kVersion: String by project
val jacksonVersion: String by project
val javaFakerVersion: String by project

group = "com.envylabs.cautiousengine"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "com.envylabs.cautiousengine.AppKt"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(platform("org.http4k:http4k-bom:$http4kVersion"))
    implementation("org.http4k:http4k-core:$http4kVersion")
    implementation("org.http4k:http4k-server-undertow:$http4kVersion")
    implementation("org.http4k:http4k-format-jackson:$http4kVersion")

    // Jackson modules for serialization & deserialization
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    // The JSR310 dependency is required to properly serializing and deserializing Java 8 date & time objects
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

    // Instrumentation
    implementation("org.http4k:http4k-metrics-micrometer:$http4kVersion")
    implementation("io.micrometer:micrometer-registry-prometheus:1.6.4")

    // JavaFaker for random data
    implementation("com.github.javafaker:javafaker:$javaFakerVersion")
}
