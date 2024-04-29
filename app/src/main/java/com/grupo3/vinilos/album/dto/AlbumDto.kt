package com.grupo3.vinilos.album.dto

import java.util.Date

data class AlbumDto(
    val id: Int,
    val name: String,
    val releaseDate: Date,
    val cover: String,
    val description: String,
    val genre: String,
    val recordLabel: String,
)