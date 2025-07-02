package com.tilawah.radio_service

import com.tilawah.apiVersion
import com.tilawah.authBearer
import com.tilawah.client
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.radioRoute() {

    route("$apiVersion/radios") {
        authenticate(authBearer) {
            get {
                val language = call.parameters["language"] ?: "en"
                val result = fetchRadios(language)
                when (result) {
                    is PubApiResult.Success -> {
                        val radioList = result.data
                        call.respond(status = HttpStatusCode.Accepted, radioList)
                    }
                    is PubApiResult.Failure -> {
                        call.respondText("Failed to fetch radio stations: ${result.exception.toString()}", status = io.ktor.http.HttpStatusCode.InternalServerError)
                    }
                }
            }

            get("/short-list") {
                val language = call.parameters["language"] ?: "en"
                val response = client.get("https://www.mp3quran.net/api/v3/radios") {
                    url {
                        parameters.append("language", language)
                    }
                }
                if (response.status.isSuccess()) {
                    val radioList = response.body<RadioListDTO>()
                    val shortRadioList = radioList.radios.filter {
                        it.name.contains("Khalifa", ignoreCase = true) ||
                                it.name.contains("خليفة الطنيجي", ignoreCase = true) ||
                                it.name.contains("Maher Al Meaqli", ignoreCase = true) ||
                                it.name.contains("ماهر المعيقلي", ignoreCase = true) ||
                                it.name.contains("Shatri", ignoreCase = true) ||
                                it.name.contains("أبو بكر الشاطري", ignoreCase = true) ||
                                it.name.contains("مشاري", ignoreCase = true) ||
                                it.name.contains("Mishary", ignoreCase = true)
                    }
                    call.respond(status = HttpStatusCode.Accepted, shortRadioList)
                } else {
                    call.respondText("Failed to fetch radio stations", status = response.status)
                }
            }
        }
    }
}

suspend fun fetchRadios(language: String): PubApiResult<List<RadioDTO>> {
    return try {
        val response = client.get("https://www.mp3quran.net/api/v3/radios") {
            url { parameters.append("language", language) }
        }

        if (response.status.isSuccess()) {
            val radioList = response.body<RadioListDTO>()
            PubApiResult.Success(radioList.radios)
        } else {
            PubApiResult.Failure(Exception("API returned ${response.status}"))
        }

    } catch (e: Exception) {
        PubApiResult.Failure(Exception("API returned ${e.toString()}"))
    }
}

sealed class PubApiResult<out T> {
    data class Success<out T>(val data: T) : PubApiResult<T>()
    data class Failure(val exception: Exception) : PubApiResult<Nothing>()
}