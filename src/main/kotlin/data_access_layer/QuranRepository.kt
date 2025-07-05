package com.tilawah.data_access_layer

import com.tilawah.URLS.QURAN_URL
import com.tilawah.configuration.client
import io.ktor.client.request.*
import io.ktor.client.statement.*

class QuranRepository {

    suspend fun getQuranData(): HttpResponse {
        val response = client.get(QURAN_URL) {
            url {
                parameters.append("language", "en")
            }
        }
        return response
    }

    suspend fun getSurahById(surahId: Int?): HttpResponse {
        val response = client.get("https://quranapi.pages.dev/api/${surahId}.json")
        return response
    }
}