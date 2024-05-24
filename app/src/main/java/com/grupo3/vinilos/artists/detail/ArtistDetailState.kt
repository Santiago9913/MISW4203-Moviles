package com.grupo3.vinilos.artists.detail

import com.grupo3.vinilos.artists.dto.ArtistDto

data class ArtistDetailState(
    val artist: ArtistDto? = ArtistDto(0, "", "", "", null, null),
    val errorMessage: String? = null
)