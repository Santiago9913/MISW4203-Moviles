package com.grupo3.vinilos.album.service

import com.grupo3.vinilos.album.dto.AlbumDto
import retrofit2.http.GET

interface AlbumService {
    @GET("albums")
    suspend fun getAlbums(): List<AlbumDto>
}