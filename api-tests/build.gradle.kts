// Defined in `gradle.properties`
val restAssuredVersion: String by project
val jUnitVersion: String by project

plugins {
    kotlin("jvm") version "1.4.20"
    id("com.adarshr.test-logger") version "2.0.0"
}

group = "com.cautiousengine"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation("io.rest-assured:rest-assured:${restAssuredVersion}")
    testImplementation("io.rest-assured:json-schema-validator:${restAssuredVersion}")
    testImplementation("io.rest-assured:kotlin-extensions:${restAssuredVersion}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${jUnitVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${jUnitVersion}")
}
