plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2024.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-aop") // AOP for @CircuitBreaker
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.github.oshai:kotlin-logging-jvm:7.0.3")
	implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
	// spring-cloud-starter-circuitbreaker-resilience4j-3.2.0.jar
	// - org.springframework.cloud:spring-cloud-starter:4.2.0
	// - org.springframework.cloud:spring-cloud-circuitbreaker-resilience4j:3.2.0
	// - io.github.resilience4j:resilience4j-circuitbreaker:2.2.0
	// - io.github.resilience4j:resilience4j-timelimiter:2.2.0
	//   - io.github.resilience4j:resilience4j-spring-boot3:2.2.0
	//     - io.github.resilience4j:resilience4j-spring6:2.2.0
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
