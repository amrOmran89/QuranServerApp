package com.tilawah

import com.tilawah.dtos.ReciterListDto

fun Int.toMp3Format(): String {
    return String.format("%03d", this) + ".mp3"
}

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