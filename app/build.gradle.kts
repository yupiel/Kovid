import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.4.30"

    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    // Select the target JVM version for compiling
    kotlinOptions.jvmTarget = "15"
}

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // This dependency is used by the application.
    implementation("com.google.guava:guava:29.0-jre")
    // Dependency for CSV parsing
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:0.15.0")
    // Dependency for JSON parsing
    implementation("com.beust:klaxon:5.0.1")

    // Dependency used only by tests for JUnit imports
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")

    // Dependency only used during runtime of tests
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

application {
    // Define the main class for the application.
    mainClass.set("de.yupiel.kovid.AppKt")
}

// Declare test task
tasks.test {
    // Enables usage of the JUnit testing framework for tests
    useJUnitPlatform()
    // Enables logging for specific events during testing
    testLogging {
        events("passed", "skipped", "failed")
    }
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}