package com.tilawah.reciter_service

import com.tilawah.client
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Routing.reciterRoute() {
    route("/reciters") {
        get("/all") {
            val language = call.parameters["language"] ?: "en"
            val response = client.get("https://www.mp3quran.net/api/v3/reciters") {
                url {
                    parameters.append("language", language)
                }
            }

            if (response.status.isSuccess()) {
                val reciterDto = response.body<ReciterListDto>()
                call.respond(reciterDto)
            } else {
                call.respondText("Failed to fetch reciters", status = response.status)
            }
        }
    }

    get("/id/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id == null) {
            call.respondText("Invalid reciter ID", status = io.ktor.http.HttpStatusCode.BadRequest)
            return@get
        }

        val language = call.parameters["language"] ?: "en"
        val response = client.get("https://www.mp3quran.net/api/v3/reciters/$id") {
            url {
                parameters.append("language", language)
            }
        }

        if (response.status.isSuccess()) {
            val reciterDto = response.body<ReciterListDto>()
            call.respond(reciterDto)
        } else {
            call.respondText("Failed to fetch reciter with ID $id", status = response.status)
        }
    }
}