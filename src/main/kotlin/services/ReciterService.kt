package com.tilawah.services

import com.tilawah.data_access_layer.ReciterRepository
import io.ktor.client.statement.*

interface IReciterService {
    suspend fun getAllReciters(query: String): HttpResponse
    suspend fun getReciterById(id: Int, query: String): HttpResponse
}

class ReciterService(val repository: ReciterRepository): IReciterService {

    override suspend fun getAllReciters(query: String): HttpResponse {
        return repository.getAll(query)
    }

    override suspend fun getReciterById(
        id: Int,
        query: String
    ): HttpResponse {
        return repository.getById(id, query)
    }
}