plugins {
    application
    kotlin("jvm") version "1.5.21"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("org.jmailen.kotlinter") version "3.3.0"
}

val jacksonVersion: String by project
val javaFakerVersion: String by project
val ktorVersion: String by project
val kodeinForKtorVersion: String by project
val logbackVersion: String by project
val prometheusVersion: String by project

group = "com.envylabs.cautiousengine"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-metrics:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")

    // Kodein for ktor
    implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:$kodeinForKtorVersion")

    // Jackson modules for serialization & deserialization
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    // The JSR310 dependency is required to properly serializing and deserializing Java 8 date & time objects
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

    // Instrumentation
    implementation("io.ktor:ktor-metrics-micrometer:$ktorVersion")
    implementation("io.micrometer:micrometer-registry-prometheus:$prometheusVersion")

    // JavaFaker for random data
    implementation("com.github.javafaker:javafaker:$javaFakerVersion")
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "15"
}
