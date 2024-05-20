package com.grupo3.vinilos.album.register

import com.grupo3.vinilos.album.dto.AlbumDto
import java.util.Date

data class AlbumsRegistrationState (
    val album: AlbumDto = AlbumDto(0, "", Date("01/01/200"), "", "", "", ""),
    val errorMessage: String? = null,
    val succeddMessage: String? = null
)