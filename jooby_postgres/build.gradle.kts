plugins {
    application
    kotlin("jvm") version "1.4.21"
    id("io.jooby.run") version "2.9.4"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("org.jmailen.kotlinter") version "3.3.0"
}

val jacksonVersion: String by project
val javaFakerVersion: String by project
val joobyVersion: String by project
val kotlinVersion: String by project

group = "com.envylabs.cautiousengine"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClassName = "com.envylabs.cautiousengine.AppKt"
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("io.jooby:jooby-netty:$joobyVersion")
    implementation("io.jooby:jooby-jackson:$joobyVersion")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    // Jackson modules for serialization & deserialization
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    // The JSR310 dependency is required to properly serializing and deserializing Java 8 date & time objects
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

    // JavaFaker for random data
    implementation("com.github.javafaker:javafaker:$javaFakerVersion")
}
