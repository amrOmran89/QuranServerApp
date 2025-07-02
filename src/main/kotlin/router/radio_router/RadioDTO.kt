package com.tilawah.router.radio_router

import kotlinx.serialization.Serializable

@Serializable
data class RadioDTO(val id: Int, val name: String, val url: String)

@Serializable
data class RadioListDTO(val radios: List<RadioDTO>)