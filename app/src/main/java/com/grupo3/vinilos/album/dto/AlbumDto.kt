package com.grupo3.vinilos.album.dto

import java.util.Date

data class AlbumDto(
    var id: Int?,
    var name: String,
    var releaseDate: Date,
    var cover: String,
    var description: String,
    var genre: String,
    var recordLabel: String,
)