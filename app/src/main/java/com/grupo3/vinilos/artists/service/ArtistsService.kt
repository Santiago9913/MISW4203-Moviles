package com.grupo3.vinilos.artists.service

import com.grupo3.vinilos.artists.dto.ArtistDto
import retrofit2.http.GET

interface ArtistsService {
    @GET("bands")
    suspend fun getBands(): List<ArtistDto>

    @GET("musicians")
    suspend fun getMusicians(): List<ArtistDto>
}