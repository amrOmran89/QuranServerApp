package com.tilawah.controller.reciter_router

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
    val surahList: List<String>
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