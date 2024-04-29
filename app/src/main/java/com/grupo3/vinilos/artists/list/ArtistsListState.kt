package com.grupo3.vinilos.artists.list

import com.grupo3.vinilos.artists.dto.ArtistDto

data class ArtistsListState(
    val artists: List<ArtistDto> = emptyList<ArtistDto>(),
    val errorMessage: String? = null
) 