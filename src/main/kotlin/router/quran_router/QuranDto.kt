package com.tilawah.router.quran_router

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class QuranDto(
    override val surahName: String,
    override val surahNameArabic: String,
    override val surahNameArabicLong: String,
    override val surahNameTranslation: String,
    override val revelationPlace: RevelationPlace,
    override val totalAyah: Int
): IQuran

@Serializable
enum class RevelationPlace {
    @SerialName("Madina")
    MADINA,

    @SerialName("Mecca")
    MECCA
}

@Serializable
data class QuranText(
    override val surahName: String,
    override val surahNameArabic: String,
    override val surahNameArabicLong: String,
    override val surahNameTranslation: String,
    override val revelationPlace: RevelationPlace,
    override val totalAyah: Int,
    val surahNo: Int,
    val english: List<String>,
    var arabic1: List<String>,
    var arabic2: List<String>,
): IQuran

interface IQuran {
    val surahName: String
    val surahNameArabic: String
    val surahNameArabicLong: String
    val surahNameTranslation: String
    val revelationPlace: RevelationPlace
    val totalAyah: Int
}

typealias QuranListDto = List<QuranDto>