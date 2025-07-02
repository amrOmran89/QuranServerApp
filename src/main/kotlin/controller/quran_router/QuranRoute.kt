package com.tilawah.router.quran_router

import com.tilawah.Terms.apiVersion
import com.tilawah.Terms.authBearer
import com.tilawah.client
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Routing.quranRoute() {
    route("$apiVersion/quran") {
        authenticate(authBearer) {
            get {
                val response = client.get("https://quranapi.pages.dev/api/surah.json")
                if (response.status.isSuccess()) {
                    val result = response.body<QuranListDto>()
                    call.respond(status = HttpStatusCode.Accepted, result)
                }
            }

            get("/{id}") {
                val surahId = call.parameters["id"]?.toIntOrNull()
                if (surahId == null) {
                    call.respondText("Invalid reciter ID", status = HttpStatusCode.BadRequest)
                    return@get
                }
                val response = client.get("https://quranapi.pages.dev/api/${surahId}.json")
                if (response.status.isSuccess()) {
                    val result = response.body<QuranText>()
                    call.respond(status = HttpStatusCode.Accepted, result)
                }
            }
        }
    }
}