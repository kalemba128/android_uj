plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "org.kalemba128"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val exposedVersion: String by project
val ktorVersion: String by project

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-crypt:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jodatime:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-json:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-money:$exposedVersion")
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-auth-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-gson-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-cors:$ktorVersion")
    implementation("io.ktor:ktor-server-freemarker:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("org.slf4j:slf4j-log4j12:2.0.9")
    implementation("com.h2database:h2:2.2.224")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}