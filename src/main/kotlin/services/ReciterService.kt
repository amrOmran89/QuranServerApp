package com.tilawah.services

import com.tilawah.controller.reciter_router.ReciterListDto
import com.tilawah.controller.reciter_router.shortList
import com.tilawah.data_access_layer.ReciterRepository
import com.tilawah.entities.ReciterListEntity
import com.tilawah.entity_dto_mapper.toDto
import io.ktor.client.call.*
import io.ktor.http.*

sealed interface ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>
    data class Error(val status: HttpStatusCode) : ResultState<Nothing>
}

class ReciterService(val repository: ReciterRepository) {

    suspend fun getAllReciters(query: String): ResultState<ReciterListDto> {
        val response = repository.getAll(query)
        return if (response.status.isSuccess()) {
            val reciterList = response.body<ReciterListEntity>()
            ResultState.Success(reciterList.toDto())
        } else {
            ResultState.Error(response.status)
        }
    }

    suspend fun getRecitersShortList(query: String): ResultState<ReciterListDto> {
        val response = repository.getAll(query)
        return if (response.status.isSuccess()) {
            val reciterList = response.body<ReciterListEntity>()
            val shortReciterList = reciterList.toDto().shortList()
            ResultState.Success(shortReciterList)
        } else {
            ResultState.Error(response.status)
        }
    }

    suspend fun getReciterById(id: Int, query: String): ResultState<ReciterListDto> {
        val response = repository.getById(id, query)
        return if (response.status.isSuccess()) {
            val reciter = response.body<ReciterListEntity>()
            ResultState.Success(reciter.toDto())
        } else {
            ResultState.Error(response.status)
        }
    }
}