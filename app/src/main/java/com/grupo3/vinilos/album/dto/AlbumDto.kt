package com.grupo3.vinilos.album.dto

data class AlbumDto(
    val id: Int,
    val name: String,
    val cover: String,
    val description: String,
    val genre: String,
    val recordLabel: String,
)