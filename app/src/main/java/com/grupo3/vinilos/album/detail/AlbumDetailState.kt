package com.grupo3.vinilos.album.detail

import com.grupo3.vinilos.album.dto.AlbumDto
import com.grupo3.vinilos.album.dto.SongDto
import java.util.Date

data class AlbumDetailState(
    val songs: List<SongDto> = emptyList(),
    val albumId: Int? = null,
    val album: AlbumDto? = null,
    val name: String? = null,
    val releaseDate: Date? = null,
    val cover: String? = null,
    val description: String? = null,
    val genre: String? = null,
    val recordLabel: String? = null,
    val errorMessage: String? = null
)