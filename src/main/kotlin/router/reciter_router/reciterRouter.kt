package com.tilawah.router.reciter_router

import com.tilawah.Terms.apiVersion
import com.tilawah.Terms.authBearer
import com.tilawah.client
import com.tilawah.data_access_layer.ReciterRepository
import com.tilawah.services.ReciterService
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.reciterRoute() {
    route("$apiVersion/reciters") {
        authenticate(authBearer) {

            // Endpoint to get all reciters
            get {
                val language = call.parameters["language"] ?: "en"
                val response = ReciterService(ReciterRepository()).getAllReciters(language)
                if (response.status.isSuccess()) {
                    val reciterDto = response.body<ReciterListDto>()
                    call.respond(reciterDto)
                } else {
                    call.respondText("Failed to fetch reciters", status = response.status)
                }
            }

            // Endpoint to get a specific reciter by ID
            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()?.let { id ->
                    val language = call.parameters["language"] ?: "en"
                    val response = ReciterService(ReciterRepository()).getReciterById(id, language)
                    if (response.status.isSuccess()) {
                        val reciterDto = response.body<ReciterDto>()
                        call.respond(reciterDto)
                    } else {
                        call.respondText("Failed to fetch reciter with ID $id", status = response.status)
                    }
                } ?: return@get
            }

            // Endpoint to get a short list of reciters
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
}