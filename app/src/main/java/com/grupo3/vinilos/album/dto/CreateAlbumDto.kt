package com.grupo3.vinilos.album.dto

import java.util.Date

data class CreateAlbumDto(
    val name: String,
    val cover: String,
    val releaseDate: Date,
    val description: String,
    val genre: String,
    val recordLabel: String,
)