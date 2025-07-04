package com.tilawah.data_access_layer

import com.tilawah.URLS.RECITER_URL
import com.tilawah.configuration.client
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class ReciterRepository {
    suspend fun getAll(query: String): HttpResponse {
        val response = client.get(RECITER_URL) {
            url {
                parameters.append("language", query)
            }
        }
        return response.body()
    }

    suspend fun getById(id: Int, query: String): HttpResponse {
        val response = client.get(RECITER_URL) {
            url {
                parameters.append("language", query)
                parameters.append("reciter", id.toString())
            }
        }
        return response.body()
    }
}