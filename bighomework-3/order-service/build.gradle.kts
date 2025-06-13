plugins {
	id("java")
	id("org.springframework.boot") version "3.5.0"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "1.0.0"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}
springBoot {
	mainClass.set("com.example.orderservice.OrderServiceApplication")
}
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	implementation("org.postgresql:postgresql")
	implementation("org.liquibase:liquibase-core")


	implementation("org.springframework.amqp:spring-rabbit")


	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")


	implementation("org.springframework.boot:spring-boot-starter-actuator")


	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.amqp:spring-rabbit-test")
	testImplementation("org.testcontainers:postgresql:1.19.3")
	testImplementation("org.testcontainers:junit-jupiter:1.19.3")
}
tasks.test {
	enabled = false
}
tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootJar {
	archiveFileName.set("order-service.jar")
}