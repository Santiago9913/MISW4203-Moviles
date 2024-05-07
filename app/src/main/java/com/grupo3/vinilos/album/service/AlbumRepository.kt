package com.grupo3.vinilos.album.service

import com.grupo3.vinilos.album.dto.AlbumDto
import com.grupo3.vinilos.album.dto.SongDto
import com.grupo3.vinilos.network.RetroFitInstance

class AlbumRepository {
    private val albumsService = RetroFitInstance.albumsService

    suspend fun getAlbums(): List<AlbumDto> {
        val data = albumsService.getAlbums();
        return data;
    }

    suspend fun getAlbumsById(albumId: Int): AlbumDto {
        return albumsService.getAlbumById(albumId);
    }

    suspend fun getSongs(albumId: Int): List<SongDto> {
        return albumsService.getSongs(albumId)
    }
}