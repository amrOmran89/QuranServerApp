package com.tilawah.controller.reciter_router

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

fun ReciterListDto.shortList(): ReciterListDto {
    val shortReciters = this.reciters.filter {
        it.name.contains("Khalifa", ignoreCase = true) ||
                it.name.contains("Maher Al Meaqli", ignoreCase = true) ||
                it.name.contains("Shatri", ignoreCase = true) ||
                it.name.contains("Mahmoud Khalil", ignoreCase = true) ||
                it.name.contains("Mishary", ignoreCase = true) ||
                it.name.contains("Siddiq Al-Minshawi", ignoreCase = true) ||
                it.name.contains("خليفة الطنيجي", ignoreCase = true) ||
                it.name.contains("أبو بكر الشاطري", ignoreCase = true) ||
                it.name.contains("ماهر المعيقلي", ignoreCase = true) ||
                it.name.contains("محمود خليل", ignoreCase = true) ||
                it.name.contains("صديق المنشاوي", ignoreCase = true) ||
                it.name.contains("مشاري", ignoreCase = true)
    }
    return ReciterListDto(shortReciters)
}