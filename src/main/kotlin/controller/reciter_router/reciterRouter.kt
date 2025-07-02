package com.tilawah.router.reciter_router

import com.tilawah.Terms.apiVersion
import com.tilawah.Terms.authBearer
import com.tilawah.data_access_layer.ReciterRepository
import com.tilawah.services.ReciterService
import com.tilawah.services.ResultState
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
                when (response) {
                    is ResultState.Success -> {
                        val reciterDto = response.data
                        call.respond(reciterDto)
                    }
                    is ResultState.Error -> {
                        call.respondText("Failed to fetch reciters", status = response.status)
                    }
                }
            }

            // Endpoint to get a specific reciter by ID
            get("/{id}") {
                call.parameters["id"]?.toIntOrNull()?.let { id ->
                    val language = call.parameters["language"] ?: "en"
                    val response = ReciterService(ReciterRepository()).getReciterById(id, language)
                    when (response) {
                        is ResultState.Success -> {
                            val reciterDto = response.data
                            call.respond(reciterDto)
                        }
                        is ResultState.Error -> {
                            call.respondText("Failed to fetch reciters", status = response.status)
                        }
                    }
                } ?: run {
                    call.respondText("Invalid reciter ID", status = io.ktor.http.HttpStatusCode.BadRequest)
                }
            }

            // Endpoint to get a short list of reciters
            get("/short-list") {
                val language = call.parameters["language"] ?: "en"
                val response = ReciterService(ReciterRepository()).getRecitersShortList(language)

                when (response) {
                    is ResultState.Success -> {
                        val reciterDto = response.data
                        call.respond(reciterDto)
                    }
                    is ResultState.Error -> {
                        call.respondText("Failed to fetch reciters", status = response.status)
                    }
                }
            }
        }
    }
}