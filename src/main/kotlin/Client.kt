package com.tilawah

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


val client = HttpClient(CIO) {
    install(plugin = ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}