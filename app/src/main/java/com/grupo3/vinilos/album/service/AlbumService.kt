package com.grupo3.vinilos.album.service

import com.grupo3.vinilos.album.dto.AlbumDto
import com.grupo3.vinilos.album.dto.SongDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {
    @GET("albums")
    suspend fun getAlbums(): List<AlbumDto>

    @GET("albums/{albumId}")
    suspend fun getAlbumById(@Path("albumId") albumId: Int): AlbumDto

    @GET("{albumId}/tracks")
    suspend fun getSongs(@Path("albumId") albumId: Int?): List<SongDto>
}