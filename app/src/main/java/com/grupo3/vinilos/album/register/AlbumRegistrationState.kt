package com.grupo3.vinilos.album.register

import com.grupo3.vinilos.album.dto.AlbumDto
import java.util.Date

data class AlbumsRegistrationState (
    val errorMessage: String? = null,
    val succeddMessage: String? = null,
    var loading:Boolean = false
)