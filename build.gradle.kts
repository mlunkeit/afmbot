plugins {
    kotlin("jvm") version "2.1.20"
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