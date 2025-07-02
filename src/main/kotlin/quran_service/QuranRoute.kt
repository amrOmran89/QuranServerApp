package com.tilawah.quran_service

import com.tilawah.apiVersion
import com.tilawah.client
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route


fun Routing.quranRoute() {
    route("$apiVersion/quran") {
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