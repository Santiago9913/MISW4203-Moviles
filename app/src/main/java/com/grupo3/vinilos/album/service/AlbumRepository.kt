package com.grupo3.vinilos.album.service

import android.util.Log
import com.grupo3.vinilos.album.dto.AlbumDto
import com.grupo3.vinilos.album.dto.SongDto
import com.grupo3.vinilos.network.CacheManager
import com.grupo3.vinilos.network.RetroFitInstance

class AlbumRepository {
    private val albumsService = RetroFitInstance.albumsService

    suspend fun getAlbums(): List<AlbumDto> {
        return albumsService.getAlbums()
    }

    suspend fun getAlbumsById(albumId: Int): AlbumDto {
        return albumsService.getAlbumById(albumId)
    }

    suspend fun getSongs(albumId: Int): List<SongDto> {
        val potentialSongs = CacheManager.getInstance().getSongs(albumId)
        return if(potentialSongs.isEmpty()){
            Log.d("Cache decision", "get from network")
            val songs = albumsService.getSongs(albumId)
            CacheManager.getInstance().addSongs(albumId, songs)
            songs
        } else{
            Log.d("Cache decision", "return ${potentialSongs.size} elements from cache")
            potentialSongs
        }
    }
}