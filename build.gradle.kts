
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com.tilawah"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.auth)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    // Ktor HTTP client core
    implementation(libs.ktor.client.core)
    // CIO engine (asynchronous engine for HTTP client)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.serialization.kotlinx.json)

    // JSON serialization support for the client
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    // Ktor OpenAPI Generator
    implementation(libs.ktor.server.openapi)
    implementation(libs.ktor.server.swagger)
    implementation("io.swagger.codegen.v3:swagger-codegen-generators:1.0.36")
}
