plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.10"
    id("org.jetbrains.kotlin.kapt") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("io.micronaut.application") version "1.3.4"
    id("org.jmailen.kotlinter") version "3.3.0"
}

version = "0.1"
group = "com.envylabs.cautiousengine"

val kotlinVersion=project.properties.get("kotlinVersion")
val jacksonVersion: String by project
val javaFakerVersion: String by project

repositories {
    mavenCentral()
    jcenter()
}

micronaut {
    runtime("tomcat")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.envylabs.cautiousengine.*")
    }
}

dependencies {
    implementation("io.micronaut:micronaut-validation")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut:micronaut-runtime")
    implementation("javax.annotation:javax.annotation-api")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut.micrometer:micronaut-micrometer-core")
    implementation("io.micronaut.micrometer:micronaut-micrometer-registry-prometheus")
    implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")

    // Jackson modules for serialization & deserialization
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    // The JSR310 dependency is required to properly serializing and deserializing Java 8 date & time objects
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

    // JavaFaker for random data
    implementation("com.github.javafaker:javafaker:$javaFakerVersion")

    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
}


application {
    mainClass.set("com.envylabs.cautiousengine.ApplicationKt")
}

java {
    sourceCompatibility = JavaVersion.toVersion("14")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "14"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "14"
        }
    }


}
