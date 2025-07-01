package com.tilawah.reciter_service

import com.tilawah.apiVersion
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
    route("$apiVersion/reciters") {
        get {
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

        get("/{id}") {
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
                val reciterDto = response.body<ReciterDto>()
                call.respond(reciterDto)
            } else {
                call.respondText("Failed to fetch reciter with ID $id", status = response.status)
            }
        }

        get("/short-list") {
            val language = call.parameters["language"] ?: "en"
            val response = client.get("https://www.mp3quran.net/api/v3/reciters") {
                url {
                    parameters.append("language", language)
                }
            }

            if (response.status.isSuccess()) {
                val reciterList = response.body<ReciterListDto>()
                val shortReciterList = reciterList.reciters
                    .filter {
                    it.name.contains("Khalifa", ignoreCase = true) ||
                            it.name.contains("Maher Al Meaqli", ignoreCase = true) ||
                            it.name.contains("Shatri", ignoreCase = true) ||
                            it.name.contains("Mahmoud Khalil", ignoreCase = true) ||
                            it.name.contains("Mishary", ignoreCase = true) ||
                            it.name.contains("Siddiq Al-Minshawi", ignoreCase = true) ||
                            it.name.contains("خليفة الطنيجي", ignoreCase = true) ||
                            it.name.contains("أبو بكر الشاطري", ignoreCase = true) ||
                            it.name.contains("ماهر المعيقلي", ignoreCase = true) ||
                            it.name.contains("محمود خليل", ignoreCase = true) ||
                            it.name.contains("صديق المنشاوي", ignoreCase = true) ||
                            it.name.contains("مشاري", ignoreCase = true)
                }
                call.respond(ReciterListDto(shortReciterList))
            } else {
                call.respondText("Failed to fetch reciters", status = response.status)
            }
        }
    }
}