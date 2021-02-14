import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val javaFakerVersion: String by project

plugins {
	id("org.springframework.boot") version "2.4.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.21"
	kotlin("plugin.spring") version "1.4.21"
	id("org.jmailen.kotlinter") version "3.3.0"
}

group = "com.envylabs"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_15

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Instrumentation
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("io.micrometer:micrometer-registry-prometheus")

	// Jackson modules for serialization & deserialization
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	// The JSR310 dependency is required to properly serializing and deserializing Java 8 date & time objects
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

	// JavaFaker for random data
	implementation("com.github.javafaker:javafaker:$javaFakerVersion")

	// Test dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "15"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
