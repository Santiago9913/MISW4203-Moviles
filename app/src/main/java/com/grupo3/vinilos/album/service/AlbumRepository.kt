package com.grupo3.vinilos.album.service

import android.util.Log
import com.grupo3.vinilos.album.dto.AlbumDto
import com.grupo3.vinilos.album.dto.AlbumRegistrationDto
import com.grupo3.vinilos.album.dto.SongCreateDto
import com.grupo3.vinilos.album.dto.SongDto
import com.grupo3.vinilos.network.CacheManager
import com.grupo3.vinilos.network.RetroFitInstance

class AlbumRepository {
    private val albumsService = RetroFitInstance.albumsService

    suspend fun getAlbums(): List<AlbumDto> {
        val potentialAlbums = CacheManager.getInstance().getAlbums()
        return if (potentialAlbums.isEmpty()) {
            Log.d("Cache decision", "getting albums from network")
            val albums = albumsService.getAlbums()
            CacheManager.getInstance().addAlbums(albums)
            albums
        } else {
            Log.d("Cache decision", "return ${potentialAlbums.size} elements from cache")
            potentialAlbums
        }
    }

    suspend fun createAlbum(album: AlbumRegistrationDto): AlbumDto {
        val newAlbum = albumsService.createAlbum(album = album)
        CacheManager.getInstance().addAlbum(newAlbum)
        return newAlbum
    }

    suspend fun getAlbumsById(albumId: Int): AlbumDto {
        val potentialAlbum = CacheManager.getInstance().getAlbumById(albumId)
        return if (potentialAlbum == null) {
            Log.d("Cache decision", "albumId: ${albumId} - getting album from network")
            val album = albumsService.getAlbumById(albumId)
            CacheManager.getInstance().addAlbums(listOf(album))
            album
        } else {
            Log.d("Cache decision", "albumId: ${albumId} - return element from cache")
            potentialAlbum
        }
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

    fun isAlbumValid(name:String, cover:String, genre:String, recordLabel: String, releaseDate: String, description:String): Boolean{
        return name.isNotEmpty() && cover.isNotEmpty() && genre.isNotEmpty() &&
                recordLabel.isNotEmpty() && releaseDate.isNotEmpty() && description.isNotEmpty()
    }
}