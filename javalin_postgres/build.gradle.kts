plugins {
    application
    kotlin("jvm") version "1.4.30"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("org.jmailen.kotlinter") version "3.3.0"
}

group = "com.envylabs.cautiousengine"
version = "1.0-SNAPSHOT"

application {
    // The shadowjar plugin requires use of the deprecated `mainClassName`.
    mainClassName = "com.envylabs.cautiousengine.AppKt"
}

val jacksonVersion: String by project
val javaFakerVersion: String by project
val javalinVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val micrometerVersion: String by project

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.javalin:javalin:$javalinVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    // Jackson modules for serialization & deserialization
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    // The JSR310 dependency is required to properly serializing and deserializing Java 8 date & time objects
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

    // JavaFaker for random data
    implementation("com.github.javafaker:javafaker:$javaFakerVersion")

    // Instrumentation with Micrometer
    implementation("io.micrometer:micrometer-core:$micrometerVersion")
    implementation("io.micrometer:micrometer-registry-prometheus:$micrometerVersion")
}
