val jUnitVersion: String by project
val restAssuredVersion: String by project

plugins {
    kotlin("jvm") version "1.4.30"
    id("org.jmailen.kotlinter") version "3.3.0"
}

group = "spring-boot_postgres"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testImplementation("io.rest-assured:kotlin-extensions:$restAssuredVersion")
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()

    // Show results for tests. Default behavior only reports failures.
    testLogging {
        events("passed", "skipped", "failed")
    }
}
