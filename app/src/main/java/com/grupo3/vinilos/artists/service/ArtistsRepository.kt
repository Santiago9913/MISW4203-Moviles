package com.grupo3.vinilos.artists.service

import com.grupo3.vinilos.artists.dto.ArtistDto
import com.grupo3.vinilos.network.RetroFitInstance

class ArtistsRepository {
    private val artistsService = RetroFitInstance.artistsService

    suspend fun getBands(): List<ArtistDto> {
        val data = artistsService.getBands();
        return data;
    }

    suspend fun getMusicians(): List<ArtistDto> {
        val data = artistsService.getMusicians();
        return data;
    }
}