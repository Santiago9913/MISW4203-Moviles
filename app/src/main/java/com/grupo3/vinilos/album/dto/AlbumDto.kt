package com.grupo3.vinilos.album.dto

import java.util.Date

data class AlbumDto(
    var id: Int? = null,
    var name: String,
    var releaseDate: Date,
    var cover: String,
    var description: String,
    var genre: String,
    var recordLabel: String,
)

data class AlbumRegistrationDto(
    var name: String,
    var releaseDate: Date,
    var cover: String,
    var description: String,
    var genre: String,
    var recordLabel: String,
)