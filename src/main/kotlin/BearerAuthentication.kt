package com.tilawah

import io.ktor.server.application.*
import io.ktor.server.auth.*

const val bearerTokenCredential = "iabLEcToUSentUALiNacAldwinsOUS"
const val authBearer = "auth-bearer"

fun Application.configureBearerAuthentication() {
    install(Authentication) {
        bearer(authBearer) {
            realm = "Access to the '/' path"
            authenticate { credentials ->
                // Here you would typically validate the token and return a principal if valid
                if (credentials.token == bearerTokenCredential) {
                    UserIdPrincipal("user")
                } else {
                    null // Invalid token
                }
            }
        }
    }
}