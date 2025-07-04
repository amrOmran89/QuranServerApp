package com.tilawah.configuration

import com.tilawah.router.quran_router.quranRoute
import com.tilawah.router.radio_router.radioRoute
import com.tilawah.router.reciter_router.reciterRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        radioRoute()
        reciterRoute()
        quranRoute()
    }

}
