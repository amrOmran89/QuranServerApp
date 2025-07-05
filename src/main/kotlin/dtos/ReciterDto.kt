package com.tilawah.dtos

import kotlinx.serialization.Serializable

@Serializable
data class ReciterListDto(val reciters: List<ReciterDto>)

@Serializable
data class ReciterDto(val id: Int, val name: String, val date: String, val moshaf: List<MoshafDto>)

@Serializable
data class MoshafDto(
    val id: Int,
    val name: String,
    val server: String,
    val surahTotal: Int,
    val moshafType: Int,
    val surahList: List<SurahDetailsDto>

)

@Serializable
data class SurahDetailsDto(
    val id: Int,
    val name: String,
    val revelationPlace: RevelationPlace,
    val server: String
)