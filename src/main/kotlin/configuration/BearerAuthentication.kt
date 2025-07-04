package com.tilawah.configuration

import com.tilawah.Terms.authBearer
import com.tilawah.Terms.bearerTokenCredential
import io.ktor.server.application.*
import io.ktor.server.auth.*


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