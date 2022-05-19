import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "me.ericc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // kotlin jdk8
    implementation(kotlin("stdlib-jdk8"))

    // kotlinx reflect
    implementation(kotlin("reflect"))

    testImplementation(kotlin("test"))

    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

    // oshi
    // https://github.com/oshi/oshi
    implementation("com.github.oshi", "oshi-core", "6.1.0")
}

tasks.test {
    useJUnitPlatform()
}

// KotlinCompile
tasks.withType<KotlinCompile> {
    kotlinOptions.apply {
        jvmTarget = JavaVersion.VERSION_16.toString()
        suppressWarnings = false
        verbose = true
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}

application {
    mainClass.set("MainKt")
}