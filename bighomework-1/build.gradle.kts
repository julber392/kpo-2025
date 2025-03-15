plugins {
	java
	jacoco
	//checkstyle
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "hse"
version = "0.0.1-SNAPSHOT"

/*checkstyle {
	toolVersion = "10.13.0"
	isIgnoreFailures = false
	maxWarnings = 0
	maxErrors = 0
}*/

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.0")
	implementation("com.opencsv:opencsv:5.7.1")
}
jacoco {
	toolVersion = "0.8.12"
	reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}
tasks.withType<Test> {
	useJUnitPlatform()
}
tasks.test {
	finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
	dependsOn(tasks.test) // tests are required to run before generating the report

	reports {
		xml.required = false
		csv.required = false
		html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
	}
	classDirectories.setFrom(
		files(classDirectories.files.map {
			fileTree(it) {
				// Шаблон для исключения всех классов с именем Main.class (в любых пакетах)
				exclude("**/Application.class")
			}
		})
	)
}