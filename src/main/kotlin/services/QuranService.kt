package com.tilawah.services

import com.tilawah.data_access_layer.QuranRepository
import com.tilawah.router.quran_router.QuranDto
import com.tilawah.router.quran_router.QuranListDto
import io.ktor.client.call.*
import io.ktor.http.*

class QuranService {

    suspend fun getQuranData(): ResultState<QuranListDto> {
        val repository = QuranRepository()
        val response = repository.getQuranData()
        if (response.status.isSuccess()) {
            val quranData = response.body<QuranListDto>()
            return ResultState.Success(quranData)
        } else {
            return ResultState.Error(response.status)
        }
    }

    suspend fun getSurahById(surahId: Int?): ResultState<QuranDto> {
        val repository = QuranRepository()
        val response = repository.getSurahById(surahId)
        if (response.status.isSuccess()) {
            val surahData = response.body<QuranDto>()
            return ResultState.Success(surahData)
        } else {
            return ResultState.Error(response.status)
        }
    }
}