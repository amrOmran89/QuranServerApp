package com.tilawah

import com.tilawah.configuration.configureBearerAuthentication
import com.tilawah.configuration.configureRouting
import com.tilawah.configuration.configureSerialization
import com.tilawah.configuration.openApi
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