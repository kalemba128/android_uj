
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("io.realm:realm-gradle-plugin:10.17.0")
    }
}

plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    kotlin("kapt") version "1.6.20" apply false
}


tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}