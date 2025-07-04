package com.tilawah.data_access_layer

import com.tilawah.URLS.RADIO_URL
import com.tilawah.configuration.client
import io.ktor.client.request.*
import io.ktor.client.statement.*

class RadioRepository {
    suspend fun fetchRadios(language: String): HttpResponse {
        val response = client.get(RADIO_URL) {
            url { parameters.append("language", language) }
        }
        return response
    }
}