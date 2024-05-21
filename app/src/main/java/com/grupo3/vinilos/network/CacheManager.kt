package com.grupo3.vinilos.network

import com.grupo3.vinilos.album.dto.AlbumDto
import com.grupo3.vinilos.album.dto.SongDto
import com.grupo3.vinilos.artists.dto.ArtistDto
import com.grupo3.vinilos.collector.dto.CollectorDto
import kotlinx.coroutines.delay

object CacheManager {

    private var instance: CacheManager? = null
    private val songsCache = mutableMapOf<Int, Pair<Boolean, List<SongDto>>>()
    private val albumsCache = mutableListOf<AlbumDto>()
    private val bandsCache = mutableListOf<ArtistDto>()
    private val musiciansCache = mutableListOf<ArtistDto>()
    private val collectorsCache = mutableListOf<CollectorDto>()


    @Synchronized
    fun getInstance(): CacheManager {
        return instance ?: CacheManager.also {
            instance = it
        }
    }

    suspend fun addSong(albumId: Int, song: SongDto) {
        delay(20)
        val songs = getSongs(albumId).second.toMutableList()
        songs.add(song)
        songsCache[albumId] = Pair(true, songs)
    }

    suspend fun addSongs(albumId: Int, songs: List<SongDto>) {
        delay(20)
        if (!songsCache.containsKey(albumId)) {
            songsCache[albumId] = Pair(true, songs)
        }
    }

    suspend fun getSongs(albumId: Int): Pair<Boolean, List<SongDto>> {
        delay(20)
        return songsCache[albumId] ?: Pair(false, emptyList())
    }

    suspend fun addAlbums(albums: List<AlbumDto>) {
        delay(20)
        albumsCache.addAll(albums)

    }

    suspend fun getAlbums(): List<AlbumDto> {
        delay(20)
        return albumsCache
    }

    suspend fun getAlbumById(albumId: Int): AlbumDto? {
        delay(20)
        return albumsCache.find { it.id == albumId }
    }

    suspend fun addAlbum(newAlbum: AlbumDto) {
        delay(20)
        albumsCache.add(newAlbum)
    }

    suspend fun addBands(bands: List<ArtistDto>) {
        delay(20)
        bandsCache.addAll(bands)
    }

    suspend fun addMusicians(musicians: List<ArtistDto>) {
        delay(10)
        musiciansCache.addAll(musicians)
    }

    suspend fun getBands(): List<ArtistDto> {
        delay(20)
        return bandsCache
    }

    suspend fun getMusicians(): List<ArtistDto> {
        delay(20)
        return musiciansCache
    }

    suspend fun getBandById(bandId: Int): ArtistDto? {
        delay(20)
        return bandsCache.find { it.id == bandId }
    }

    suspend fun getMusicianById(musicianId: Int): ArtistDto? {
        delay(20)
        return musiciansCache.find { it.id == musicianId }
    }

    suspend fun getCollectors(): List<CollectorDto> {
        delay(20)
        return collectorsCache
    }

    suspend fun addCollectors(collectors: List<CollectorDto>) {
        delay(20)
        collectorsCache.addAll(collectors)
    }

    suspend fun getCollectorById(collectorId: Int): CollectorDto? {
        delay(20)
        return collectorsCache.find { it.id == collectorId }
    }
}