package com.grupo3.vinilos.artists.service

import android.util.Log
import com.grupo3.vinilos.artists.dto.ArtistDto
import com.grupo3.vinilos.network.CacheManager
import com.grupo3.vinilos.network.RetroFitInstance

class ArtistsRepository {
    private val artistsService = RetroFitInstance.artistsService

    suspend fun getBands(): List<ArtistDto> {
        val potentialBands = CacheManager.getInstance().getBands()
        return if (potentialBands.isEmpty()) {
            Log.d("Cache decision", "getting bands from network")
            val bands = artistsService.getBands()
            CacheManager.getInstance().addBands(bands)
            bands
        } else {
            Log.d("Cache decision", "return ${potentialBands.size} elements from cache")
            potentialBands
        }
    }

    suspend fun getMusicians(): List<ArtistDto> {
        val potentialMusicians = CacheManager.getInstance().getMusicians()
        return if (potentialMusicians.isEmpty()) {
            Log.d("Cache decision", "getting musicians from network")
            val musicians = artistsService.getMusicians()
            CacheManager.getInstance().addMusicians(musicians)
            musicians
        } else {
            Log.d("Cache decision", "return ${potentialMusicians.size} elements from cache")
            potentialMusicians
        }
    }

    suspend fun getMusician(id: Int): ArtistDto {
        val potentialMusician = CacheManager.getInstance().getMusicianById(id)
        return if (potentialMusician == null) {
            Log.d("Cache decision", "musicianId: ${id} - getting musician from network")
            val musician = artistsService.getMusicianById(id)
            CacheManager.getInstance().addMusicians(listOf(musician))
            musician
        } else {
            Log.d("Cache decision", "musicianId: ${id} - return element from cache")
            potentialMusician
        }
    }

    suspend fun getBand(id: Int): ArtistDto {
        val potentialBand = CacheManager.getInstance().getBandById(id)
        return if (potentialBand == null) {
            Log.d("Cache decision", "bandId: ${id} - getting band from network")
            val band = artistsService.getBandById(id)
            CacheManager.getInstance().addBands(listOf(band))
            band
        } else {
            Log.d("Cache decision", "bandId: ${id} - return element from cache")
            potentialBand
        }
    }
}