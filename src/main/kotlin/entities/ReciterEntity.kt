package com.tilawah.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ReciterListEntity(val reciters: List<ReciterEntity>)

@Serializable
data class ReciterEntity(val id: Int, val name: String, val date: String, val moshaf: List<MoshafEntity>)

@Serializable
data class MoshafEntity(
    val id: Int,
    val name: String,
    val server: String,
    @SerialName("surah_total") val surahTotal: Int,
    @SerialName("moshaf_type") val moshafType: Int,
    @SerialName("surah_list") val surahList: String
)