package com.grupo3.vinilos.album.detail

import com.grupo3.vinilos.album.dto.AlbumDto
import com.grupo3.vinilos.album.dto.SongDto

data class AlbumDetailState(
    val songs: List<SongDto> = emptyList(),
    val album: AlbumDto? = null,
    val errorMessage: String? = null
)