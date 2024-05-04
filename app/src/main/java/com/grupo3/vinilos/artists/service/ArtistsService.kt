package com.grupo3.vinilos.artists.service

import com.grupo3.vinilos.artists.dto.ArtistDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistsService {
    @GET("bands")
    suspend fun getBands(): List<ArtistDto>

    @GET("musicians")
    suspend fun getMusicians(): List<ArtistDto>

    @GET("musicians/{id}")
    suspend fun getMusicianById(@Path("id") id: Int): ArtistDto

    @GET("bands/{id}")
    suspend fun getBandById(@Path("id") id: Int): ArtistDto
}