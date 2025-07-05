package com.tilawah.services

import com.tilawah.data_access_layer.ReciterRepository
import com.tilawah.dtos.ReciterDto
import com.tilawah.dtos.ReciterListDto
import com.tilawah.entities.ReciterListEntity
import com.tilawah.entities.SuwarEntityList
import com.tilawah.entity_dto_mapper.toDto
import com.tilawah.shortList
import io.ktor.client.call.*
import io.ktor.http.*

sealed interface ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>
    data class Error(val status: HttpStatusCode) : ResultState<Nothing>
}

class ReciterService(val repository: ReciterRepository) {

    suspend fun getAllReciters(query: String): ResultState<ReciterListDto> {
        val response = repository.getAll(query)
        val suwarApi = repository.getSuwar(query)
        val suwarResult = suwarApi.body<SuwarEntityList>()

        return if (response.status.isSuccess()) {
            val reciterList = response.body<ReciterListEntity>()
            ResultState.Success(reciterList.toDto(suwarResult))
        } else {
            ResultState.Error(response.status)
        }
    }

    suspend fun getRecitersShortList(query: String): ResultState<ReciterListDto> {
        val response = repository.getAll(query)
        val suwarApi = repository.getSuwar(query)
        val suwarResult = suwarApi.body<SuwarEntityList>()

        return if (response.status.isSuccess()) {
            val reciterList = response.body<ReciterListEntity>()
            val shortReciterList = reciterList.toDto(suwarResult).shortList()
            ResultState.Success(shortReciterList)
        } else {
            ResultState.Error(response.status)
        }
    }

    suspend fun getReciterById(id: Int, query: String): ResultState<ReciterDto> {

        val suwarApi = repository.getSuwar(query)
        val suwarResult = suwarApi.body<SuwarEntityList>()

        val response = repository.getById(id, query)
        if (response.status.isSuccess()) {
            val reciterListEntity = response.body<ReciterListEntity>()
            val firstReciter = reciterListEntity.reciters.firstOrNull()
            return if (firstReciter != null) {
                ResultState.Success(firstReciter.toDto(suwarResult))
            } else {
                ResultState.Error(HttpStatusCode.NotFound)
            }
        } else {
            return ResultState.Error(response.status)
        }
    }
}