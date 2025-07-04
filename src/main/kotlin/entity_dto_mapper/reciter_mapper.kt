package com.tilawah.entity_dto_mapper

import com.tilawah.controller.reciter_router.MoshafDto
import com.tilawah.controller.reciter_router.ReciterDto
import com.tilawah.controller.reciter_router.ReciterListDto
import com.tilawah.entities.MoshafEntity
import com.tilawah.entities.ReciterListEntity

fun MoshafEntity.toDto(): MoshafDto {
    val newSurahList = this.surahList.split(",").map { it.trim() }

    return MoshafDto(
        id = this.id,
        name = this.name,
        surahTotal = this.surahTotal,
        server = this.server,
        moshafType = this.moshafType,
        surahList = newSurahList
    )
}

fun ReciterListEntity.toDto(): ReciterListDto {
    return ReciterListDto(
        reciters = this.reciters.map { reciter ->
            ReciterDto(
                id = reciter.id,
                name = reciter.name,
                date = reciter.date,
                moshaf = reciter.moshaf.map { it.toDto() }
            )
        }
    )
}