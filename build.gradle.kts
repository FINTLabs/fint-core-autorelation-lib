plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.5.5" apply false
    id("io.spring.dependency-management") version "1.1.7"
    id("maven-publish")
    id("java-library")
}

group = "no.novari"
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

val fintVersion = "4.0.0-rc-3"
dependencies {
    implementation("org.springframework.boot:spring-boot-starter:3.5.5")
    implementation("no.novari:fint-model-core:1.0.0-rc-2")
    implementation("no.novari:fint-model-resource:1.0.0-rc-3")
    implementation("no.novari:fint-core-metamodel:3.0.0-rc.1")

    testImplementation("no.novari:fint-utdanning-resource-model-java:$fintVersion")
    testImplementation("no.novari:fint-administrasjon-resource-model-java:$fintVersion")
    testImplementation("no.novari:fint-personvern-resource-model-java:$fintVersion")
    testImplementation("no.novari:fint-okonomi-resource-model-java:$fintVersion")
    testImplementation("no.novari:fint-ressurs-resource-model-java:$fintVersion")
    testImplementation("no.novari:fint-arkiv-resource-model-java:$fintVersion")

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

publishing {
    repositories {
        maven {
            url = uri("https://repo.fintlabs.no/releases")
            credentials {
                username = System.getenv("REPOSILITE_USERNAME")
                password = System.getenv("REPOSILITE_PASSWORD")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
