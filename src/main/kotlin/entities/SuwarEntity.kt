package com.tilawah.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SuwarEntityList (
    val suwar: List<SuwarEntity>
)

@Serializable
data class SuwarEntity (
    val id: Int,
    val name: String,

    @SerialName("start_page")
    val startPage: Int,

    @SerialName("end_page")
    val endPage: Int,

    val makkia: Int,
    val type: Int
)

