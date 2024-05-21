package com.grupo3.vinilos.artists.detail

import com.grupo3.vinilos.artists.dto.ArtistDto

data class ArtistDetailState (
    val artist: ArtistDto? = null,
    val errorMessage: String? = null
)