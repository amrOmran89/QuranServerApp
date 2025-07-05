package com.tilawah.services

import com.tilawah.data_access_layer.RadioRepository
import com.tilawah.dtos.RadioListDTO
import io.ktor.client.call.*
import io.ktor.http.*

class RadioService {

    private val repository = RadioRepository()

    suspend fun getRadios(language: String): ResultState<RadioListDTO> {
        val response = repository.fetchRadios(language)
        return if (response.status.isSuccess()) {
            val radioList = response.body<RadioListDTO>()
            ResultState.Success(radioList)
        } else {
            ResultState.Error(response.status)
        }
    }

    suspend fun getRadiosShortList(language: String): ResultState<RadioListDTO> {
        val response = repository.fetchRadios(language)
        return if (response.status.isSuccess()) {
            val radioList = response.body<RadioListDTO>()
            val shortRadioList = radioList.radios.filter {
                it.name.contains("Khalifa", ignoreCase = true) ||
                it.name.contains("خليفة الطنيجي", ignoreCase = true) ||
                it.name.contains("Maher Al Meaqli", ignoreCase = true) ||
                it.name.contains("ماهر المعيقلي", ignoreCase = true) ||
                it.name.contains("Shatri", ignoreCase = true) ||
                it.name.contains("أبو بكر الشاطري", ignoreCase = true) ||
                it.name.contains("Mishary", ignoreCase = true) ||
                it.name.contains("مشاري", ignoreCase = true)
            }
            ResultState.Success(RadioListDTO(shortRadioList))
        } else {
            ResultState.Error(response.status)
        }
    }
}