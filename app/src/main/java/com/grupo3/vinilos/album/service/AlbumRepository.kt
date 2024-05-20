package com.grupo3.vinilos.album.service

import android.util.Log
import com.grupo3.vinilos.album.dto.AlbumDto
import com.grupo3.vinilos.album.dto.SongCreateDto
import com.grupo3.vinilos.album.dto.SongDto
import com.grupo3.vinilos.network.CacheManager
import com.grupo3.vinilos.network.RetroFitInstance
import java.util.Date

class AlbumRepository {
    private val albumsService = RetroFitInstance.albumsService

    suspend fun getAlbums(): List<AlbumDto> {
        return albumsService.getAlbums()
    }

    suspend fun createAlbum(album: AlbumDto): AlbumDto {
        return albumsService.createAlbum(album = album)
    }

    suspend fun getAlbumsById(albumId: Int): AlbumDto {
        return albumsService.getAlbumById(albumId)
    }

    suspend fun getSongs(albumId: Int): List<SongDto> {
        val potentialSongs = CacheManager.getInstance().getSongs(albumId)
        return if (!potentialSongs.first) {
            Log.d("Cache decision", "albumId: ${albumId} - getting songs from network")
            val songs = albumsService.getSongs(albumId)
            CacheManager.getInstance().addSongs(albumId, songs)
            songs
        } else {
            Log.d(
                "Cache decision",
                "albumId: ${albumId} - return ${potentialSongs.second.size} elements from cache"
            )
            potentialSongs.second
        }
    }

    suspend fun addSong(albumId: Int, song: SongCreateDto): SongDto {
        val newSong = albumsService.addSong(albumId, song)
        CacheManager.getInstance().addSong(albumId, newSong)
        return newSong
    }

    fun isAlbumValid(name:String, cover:String, genre:String, recordLabel: String, releaseDate: String): Boolean{
        return !name.isEmpty() && !cover.isEmpty() && !genre.isEmpty() && !recordLabel.isEmpty() && !releaseDate.isEmpty()
    }
}