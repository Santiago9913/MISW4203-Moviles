package com.grupo3.vinilos.network

import android.content.Context
import com.grupo3.vinilos.album.dto.SongDto

class CacheManager(context: Context) {

    companion object {
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }
    private var songs: HashMap<Int,List<SongDto>> = hashMapOf()
    fun addSongs(albumId: Int, song: List<SongDto>){
        if (!songs.containsKey(albumId)){
            songs[albumId] = song
        }
    }
    fun getSongs(albumId: Int) : List<SongDto>{
        return if (songs.containsKey(albumId)) songs[albumId]!! else listOf()
    }
}