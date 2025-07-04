package com.tilawah.router.radio_router

import com.tilawah.Terms.apiVersion
import com.tilawah.Terms.authBearer
import com.tilawah.services.RadioService
import com.tilawah.services.ResultState
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.radioRoute() {

    route("$apiVersion/radios") {
        authenticate(authBearer) {
            get {
                val language = call.parameters["language"] ?: "en"
                val service = RadioService()
                val result = service.getRadios(language)
                when (result) {
                    is ResultState.Success -> {
                        call.respond(status = HttpStatusCode.Accepted, result.data)
                    }
                    is ResultState.Error -> {
                        call.respondText("Failed to fetch radio stations", status = HttpStatusCode.InternalServerError)
                    }
                }
            }

            get("/short-list") {
                val language = call.parameters["language"] ?: "en"
                val service = RadioService()
                val result = service.getRadiosShortList(language)
                when (result) {
                    is ResultState.Success -> {
                        call.respond(status = HttpStatusCode.Accepted, result.data)
                    }
                    is ResultState.Error -> {
                        call.respondText("Failed to fetch radio stations", status = HttpStatusCode.InternalServerError)
                    }
                }
            }
        }
    }
}