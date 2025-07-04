package com.tilawah.data_access_layer

import com.tilawah.configuration.client
import io.ktor.client.request.*
import io.ktor.client.statement.*

class QuranRepository {

    suspend fun getQuranData(): HttpResponse {
        val response = client.get("https://quranapi.pages.dev/api/surah.json")
        return response
    }

    suspend fun getSurahById(surahId: Int?): HttpResponse {
        val response = client.get("https://quranapi.pages.dev/api/${surahId}.json")
        return response
    }
}