plugins {
    kotlin("jvm") version "2.1.20"

    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "dev.mlunkeit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/net.dv8tion/JDA
    implementation("net.dv8tion:JDA:5.5.1")
    // https://mvnrepository.com/artifact/com.google.genai/google-genai
    implementation("com.google.genai:google-genai:1.1.0")
}

kotlin {
    jvmToolchain(23)
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "dev.mlunkeit.afm.AFMBotKt")
    }
}

tasks.shadowJar {
    archiveClassifier.set("all")
}