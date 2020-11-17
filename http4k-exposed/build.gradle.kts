
// Defined in `gradle.properties`
val exposed_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.4.10"
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

dependencies {
    implementation(kotlin("stdlib"))
    implementation(platform("org.http4k:http4k-bom:3.268.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-netty:3.268.0")
    implementation("org.http4k:http4k-format-jackson:3.268.0")

    // Exposed ORM library
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("com.zaxxer:HikariCP:3.4.5") // JDBC Connection Pool
    implementation("org.postgresql:postgresql:42.2.1") // JDBC Connector for PostgreSQL
}
