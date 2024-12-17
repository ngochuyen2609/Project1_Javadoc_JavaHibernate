plugins {
    id("java")
    kotlin("jvm") version "1.9.0"
}

group = "ngoc.huyen.it"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // Hibernate Core
    implementation("org.hibernate:hibernate-core:6.2.0.Final")
    // JDBC Driver (ví dụ: MySQL)
    implementation("mysql:mysql-connector-java:8.0.33")
    // Kotlin Reflection (Hibernate cần reflection)
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.0")
    // Kotlin Standard Library
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17)) // Sử dụng Java 17
}

tasks.withType<Javadoc> {
    isFailOnError = false
    options {
        encoding = "UTF-8"
        var charSet = "UTF-8"
    }
}

tasks.test {
    useJUnitPlatform()
}