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
    implementation("org.agrona:agrona:2.2.0")
    implementation("com.lmax:disruptor:4.0.0")
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.0")
    implementation("co.paralleluniverse:quasar-core:0.8.0")
    implementation("org.threadly:threadly:7.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs(
        "--add-opens=java.base/java.lang=ALL-UNNAMED",
        "--add-opens=java.base/sun.instrument=ALL-UNNAMED",
        "--add-opens=java.base/jdk.internal.misc=ALL-UNNAMED",
        "--add-exports=java.base/jdk.internal.misc=ALL-UNNAMED"
    )
}