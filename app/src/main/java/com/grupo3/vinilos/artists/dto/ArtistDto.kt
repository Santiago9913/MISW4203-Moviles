package com.grupo3.vinilos.artists.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.Date

data class ArtistDto(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val creationDate: Date?,
    val birthDate: Date?,
)

