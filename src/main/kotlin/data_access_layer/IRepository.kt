package com.tilawah.data_access_layer

interface IRepository<T> {
    suspend fun getAll(query: String = ""): List<T>
    suspend fun getById(id: Int, query: String = ""): T
}