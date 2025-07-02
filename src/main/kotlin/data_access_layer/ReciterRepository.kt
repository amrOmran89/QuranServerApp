package com.tilawah.data_access_layer

import com.tilawah.client
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class ReciterRepository {
    suspend fun getAll(query: String): HttpResponse {
        val response = client.get("https://www.mp3quran.net/api/v3/reciters") {
            url {
                parameters.append("language", query)
            }
        }
        return response.body()
    }

    suspend fun getById(id: Int, query: String): HttpResponse {
        val response = client.get("https://www.mp3quran.net/api/v3/reciters/$id") {
            url {
                parameters.append("language", query)
            }
        }
        return response.body()
    }
}