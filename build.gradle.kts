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