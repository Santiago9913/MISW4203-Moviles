package com.grupo3.vinilos.network

import com.grupo3.vinilos.album.dto.SongDto

object CacheManager {

    private var instance: CacheManager? = null
    private val songsCache = mutableMapOf<Int, Pair<Boolean, List<SongDto>>>()

    @Synchronized
    fun getInstance(): CacheManager {
        return instance ?: CacheManager.also {
            instance = it
        }
    }

    fun addSong(albumId: Int, song: SongDto) {
        val songs = getSongs(albumId).second.toMutableList()
        songs.add(song)
        songsCache[albumId] = Pair(true, songs)
    }

    fun addSongs(albumId: Int, songs: List<SongDto>) {
        if (!songsCache.containsKey(albumId)) {
            songsCache[albumId] = Pair(true, songs)
        }
    }

    fun getSongs(albumId: Int): Pair<Boolean, List<SongDto>> {
        return songsCache[albumId] ?: Pair(false, emptyList())
    }
}