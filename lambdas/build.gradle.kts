/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("buildlogic.kotlin-library-conventions")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
repositories {
    mavenCentral()
}
dependencies {
    implementation("com.amazonaws:aws-lambda-java-core:1.2.0")
    implementation("com.amazonaws:aws-lambda-java-events:3.7.0")
    implementation("com.amazonaws:aws-xray-recorder-sdk-core:2.18.2")
    implementation("com.amazonaws:aws-xray-recorder-sdk-aws-sdk-v2:2.18.2")
    implementation("com.amazonaws:aws-xray-recorder-sdk-aws-sdk-v2-instrumentor:2.18.2")
    implementation("org.apache.commons:commons-text")
}
