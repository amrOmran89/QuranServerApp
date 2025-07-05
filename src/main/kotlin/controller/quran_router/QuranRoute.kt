package com.tilawah.controller.quran_router

import com.tilawah.Terms.apiVersion
import com.tilawah.Terms.authBearer
import com.tilawah.services.QuranService
import com.tilawah.services.ResultState
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Routing.quranRoute() {
    route("$apiVersion/quran") {
        authenticate(authBearer) {
            get {
                val result = QuranService().getQuranData()
                when (result) {
                    is ResultState.Success -> {
                        call.respond(status = HttpStatusCode.Accepted, result.data)
                    }
                    is ResultState.Error -> {
                        call.respondText("Failed to fetch Quran data", status = result.status)
                    }
                }
            }

            get("/{id}") {
                val surahId = call.parameters["id"]?.toIntOrNull()
                if (surahId == null) {
                    call.respondText("Invalid reciter ID", status = HttpStatusCode.BadRequest)
                    return@get
                }
                val result = QuranService().getSurahById(surahId)
                when (result) {
                    is ResultState.Success -> {
                        call.respond(status = HttpStatusCode.Accepted, result.data)
                    }
                    is ResultState.Error -> {
                        call.respondText("Failed to fetch Quran data", status = result.status)
                    }
                }
            }
        }
    }
}