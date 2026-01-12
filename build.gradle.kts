plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.5.5" apply false
    id("io.spring.dependency-management") version "1.1.7"
    id("maven-publish")
    id("java-library")
}

group = "no.fintlabs"
version = project.findProperty("version") ?: "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    withSourcesJar()
    withJavadocJar()
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

repositories {
    maven("https://repo.fintlabs.no/releases")
    mavenCentral()
}

val fintVersion = "3.21.11-rc-4"
dependencies {
    implementation("org.springframework.boot:spring-boot-starter:3.5.5")
    implementation("no.fintlabs:fint-model-core:0.5.0")
    implementation("no.fintlabs:fint-model-resource:0.5.0")
    implementation("no.fintlabs:fint-core-consumer-metamodel:2.0.0-rc-4")

    testImplementation("no.fint:fint-utdanning-resource-model-java:$fintVersion")
    testImplementation("no.fint:fint-administrasjon-resource-model-java:$fintVersion")
    testImplementation("no.fint:fint-personvern-resource-model-java:$fintVersion")
    testImplementation("no.fint:fint-okonomi-resource-model-java:$fintVersion")
    testImplementation("no.fint:fint-ressurs-resource-model-java:$fintVersion")
    testImplementation("no.fint:fint-arkiv-resource-model-java:$fintVersion")

    testImplementation("io.mockk:mockk:1.13.12")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

apply(from = "https://raw.githubusercontent.com/FINTLabs/fint-buildscripts/master/reposilite.ga.gradle")