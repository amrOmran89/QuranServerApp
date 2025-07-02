package com.tilawah.services

import com.tilawah.controller.reciter_router.ReciterListDto
import com.tilawah.controller.reciter_router.shortList
import com.tilawah.data_access_layer.ReciterRepository
import io.ktor.client.call.*
import io.ktor.http.*

interface IReciterService {
    suspend fun getAllReciters(query: String): ResultState<ReciterListDto>
    suspend fun getRecitersShortList(query: String): ResultState<ReciterListDto>
    suspend fun getReciterById(id: Int, query: String): ResultState<ReciterListDto>
}

sealed interface ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>
    data class Error(val status: HttpStatusCode) : ResultState<Nothing>
}


class ReciterService(val repository: ReciterRepository): IReciterService {

    override suspend fun getAllReciters(query: String): ResultState<ReciterListDto> {
        val response = repository.getAll(query)
        if (response.status.isSuccess()) {
            val reciterList = response.body<ReciterListDto>()
            return ResultState.Success(reciterList)
        } else {
            return ResultState.Error(response.status)
        }
    }

    override suspend fun getRecitersShortList(query: String): ResultState<ReciterListDto> {
        val response = repository.getAll(query)
        if (response.status.isSuccess()) {
            val reciterList = response.body<ReciterListDto>()
            val shortReciterList = reciterList.shortList()
            return ResultState.Success(shortReciterList)
        } else {
            return ResultState.Error(response.status)
        }
    }

    override suspend fun getReciterById(id: Int, query: String): ResultState<ReciterListDto> {
        val response = repository.getById(id, query)
        if (response.status.isSuccess()) {
            val reciter = response.body<ReciterListDto>()
            return ResultState.Success(reciter)
        } else {
            return ResultState.Error(response.status)
        }
    }
}