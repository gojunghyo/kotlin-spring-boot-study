import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.6.21"

    id("org.springframework.boot") version "2.7.10"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("io.freefair.lombok") version "5.2.1"

    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"

    // KAPT(Kotlin Annotation Processing Tool)를 설치합니다
    kotlin("kapt") version kotlinVersion
    // Intellij에서 사용할 파일을 생성하는 플러그인입니다
    idea
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.mysql:mysql-connector-j:8.0.32")
    compileOnly ("org.projectlombok:lombok:1.18.20")
    annotationProcessor ("org.projectlombok:lombok:1.18.20")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("org.springdoc:springdoc-openapi-ui:1.6.8")
    implementation("org.springdoc:springdoc-openapi-webmvc-core:1.6.8")

    runtimeOnly ("com.h2database:h2")
    compileOnly ("org.flywaydb:flyway-core")

    // querydsl을 설치합니다
    implementation("com.querydsl:querydsl-jpa:5.0.0")
    //    KAPT(Kotlin Annotation Processing Tool)를 설정합니다
    //    kotlin 코드가 아니라면 kapt 대신 annotationProcessor를 사용합니다
    //    JPAAnnotationProcessor를 사용하기 위해 마지막에 :jpa를 붙입니다
    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")


    testImplementation ("org.assertj:assertj-core:3.20.2")
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

kapt {
    arguments {
        arg("querydsl.entityPathResolver", "com.querydsl.core.types.dsl.PathBuilder")
    }
}

// QClass를 Intellij가 사용할 수 있도록 경로에 추가합니다
idea {
    module {
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}