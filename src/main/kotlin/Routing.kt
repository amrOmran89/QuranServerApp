package com.tilawah

import com.tilawah.quran_service.quranRoute
import com.tilawah.radio_service.radioRoute
import com.tilawah.reciter_service.reciterRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        radioRoute()
        reciterRoute()
        quranRoute()
    }

}
