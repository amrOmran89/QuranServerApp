package com.tilawah

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureBearerAuthentication()
    configureRouting()
    openApi()
}

const val apiVersion = "/api/v1"