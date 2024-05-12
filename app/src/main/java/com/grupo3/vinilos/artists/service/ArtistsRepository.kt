package com.grupo3.vinilos.artists.service

import com.grupo3.vinilos.artists.dto.ArtistDto
import com.grupo3.vinilos.network.RetroFitInstance

class ArtistsRepository {
    private val artistsService = RetroFitInstance.artistsService

    suspend fun getBands(): List<ArtistDto> {
        return artistsService.getBands()
    }

    suspend fun getMusicians(): List<ArtistDto> {
        return artistsService.getMusicians()
    }

    suspend fun getMusician(id: Int): ArtistDto {
        return artistsService.getMusicianById(id)
    }

    suspend fun getBand(id: Int): ArtistDto {
        return artistsService.getBandById(id)
    }
}