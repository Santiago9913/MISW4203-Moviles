package com.grupo3.vinilos.network

import com.grupo3.vinilos.album.dto.SongDto

object CacheManager {

    private var instance: CacheManager? = null
    private var songs: HashMap<Int, List<SongDto>> = hashMapOf()

    @Synchronized
    fun getInstance(): CacheManager {
        return instance ?: CacheManager.also {
            instance = it
        }
    }

    fun addSongs(albumId: Int, song: List<SongDto>) {
        if (!songs.containsKey(albumId)) {
            songs[albumId] = song
        }
    }

    fun getSongs(albumId: Int): List<SongDto> {
        return songs[albumId] ?: listOf()
    }
}