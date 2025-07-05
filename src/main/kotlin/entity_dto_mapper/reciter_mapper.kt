package com.tilawah.entity_dto_mapper

import com.tilawah.dtos.*
import com.tilawah.entities.*
import com.tilawah.toMp3Format

fun MoshafEntity.toDto(suwar: SuwarEntityList): MoshafDto {
    val newSurahList = this.surahList.split(",").map { it.trim().toInt() }
    val availableMoshaf = suwar.suwar.filter { it.id in newSurahList }.map {
        val suraServer = this.server + it.id.toMp3Format()
        it.toDto(suraServer)
    }


    return MoshafDto(
        id = this.id,
        name = this.name,
        surahTotal = this.surahTotal,
        server = this.server,
        moshafType = this.moshafType,
        surahList = availableMoshaf
    )
}

fun SuwarEntity.toDto(suraServer: String): SurahDetailsDto {
    return SurahDetailsDto(
        id = this.id,
        name = this.name,
        revelationPlace = if (this.makkia == 0)  RevelationPlace.MECCA else RevelationPlace.MADINA,
        server = suraServer
    )
}

fun ReciterListEntity.toDto(suwar: SuwarEntityList): ReciterListDto {
    return ReciterListDto(
        reciters = this.reciters.map { reciter ->
            ReciterDto(
                id = reciter.id,
                name = reciter.name,
                date = reciter.date,
                moshaf = reciter.moshaf.map { it.toDto(suwar) }
            )
        }
    )
}

fun ReciterEntity.toDto(suwar: SuwarEntityList): ReciterDto {
    return ReciterDto(
        id = this.id,
        name = this.name,
        date = this.date,
        moshaf = this.moshaf.map { it.toDto(suwar) }
    )
}