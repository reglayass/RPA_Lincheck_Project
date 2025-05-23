plugins {
    kotlin("jvm") version "2.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:lincheck:2.39")
    testImplementation("junit:junit:4.13.1")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:1.9.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.9.2")
    implementation("org.jctools:jctools-core:4.0.3")
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.0")
    implementation("com.google.guava:guava:33.4.8-jre")
    implementation("org.agrona:agrona:2.1.0")
}

tasks.test {
    useJUnitPlatform()
}