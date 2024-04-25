package com.grupo3.vinilos.artists.dto

import java.time.LocalDate
import java.util.Date

data class ArtistDto(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: LocalDate?,
    val creationDate: LocalDate?,
    val type: String,
    val bandId: String?,
) {

}