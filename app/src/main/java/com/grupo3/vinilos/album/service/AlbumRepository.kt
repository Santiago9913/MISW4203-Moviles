package com.grupo3.vinilos.album.service

import android.util.Log
import com.grupo3.vinilos.album.dto.AlbumDto
import com.grupo3.vinilos.album.dto.SongDto
import com.grupo3.vinilos.network.CacheManager
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
        var potentialSongs = CacheManager.getInstance(application.applicationContext).getSongs(albumId)
        if(potentialSongs.isEmpty()){
            Log.d("Cache decision", "get from network")
            var songs = albumsService.getSongs(albumId)
            CacheManager.getInstance(application.applicationContext).addSongs(albumId, songs)
            return songs
        }
        else{
            Log.d("Cache decision", "return ${potentialSongs.size} elements from cache")
            return potentialSongs
        }
    }
}