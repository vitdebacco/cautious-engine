
// Defined in `gradle.properties`
val exposedVersion: String by project
val hikariVersion: String by project
val http4kVersion: String by project
val kotestVersion: String by project
val logbackVersion: String by project
val postgresConnectorVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.4.10"
    id("com.adarshr.test-logger") version "2.0.0"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "com.example"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "com.example.ApplicationKt"
}

repositories {
    mavenCentral()
    maven { url = uri("https://dl.bintray.com/kotlin/exposed") }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(platform("org.http4k:http4k-bom:$http4kVersion"))
    implementation("org.http4k:http4k-core:$http4kVersion")
    implementation("org.http4k:http4k-server-netty:$http4kVersion")
    implementation("org.http4k:http4k-format-jackson:$http4kVersion")

    // Exposed ORM library
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation("com.zaxxer:HikariCP:$hikariVersion") // JDBC Connection Pool
    implementation("org.postgresql:postgresql:$postgresConnectorVersion") // JDBC Connector for PostgreSQL

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
//    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.mockk:mockk:1.9.3")
}
