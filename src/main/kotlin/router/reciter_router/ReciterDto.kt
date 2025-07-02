package com.tilawah.router.reciter_router

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReciterListDto(val reciters: List<ReciterDto>)

@Serializable
data class ReciterDto(val id: Int, val name: String, val date: String, val moshaf: List<Moshaf>)

@Serializable
data class Moshaf(
    val id: Int,
    val name: String,
    val server: String,
    @SerialName("surah_total") val surahTotal: Int,
    @SerialName("moshaf_type") val moshafType: Int,
    @SerialName("surah_list") val surahList: String
)
