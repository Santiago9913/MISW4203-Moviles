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

    fun addAlbums(albums: List<AlbumDto>) {
        albumsCache.addAll(albums)

    }

    fun getAlbums(): List<AlbumDto> {
        return albumsCache
    }

    fun getAlbumById(albumId: Int): AlbumDto? {
        return albumsCache.find { it.id == albumId }
    }

    fun addAlbum(newAlbum: AlbumDto) {
        albumsCache.add(newAlbum)
    }

    fun addBands(bands: List<ArtistDto>) {
        bandsCache.addAll(bands)
    }

    fun addMusicians(musicians: List<ArtistDto>) {
        musiciansCache.addAll(musicians)
    }

    fun getBands(): List<ArtistDto> {
        return bandsCache
    }

    fun getMusicians(): List<ArtistDto> {
        return musiciansCache
    }

    fun getBandById(bandId: Int): ArtistDto? {
        return bandsCache.find { it.id == bandId }
    }

    fun getMusicianById(musicianId: Int): ArtistDto? {
        return musiciansCache.find { it.id == musicianId }
    }

    fun getCollectors(): List<CollectorDto> {
        return collectorsCache
    }

    fun addCollectors(collectors: List<CollectorDto>) {
        collectorsCache.addAll(collectors)
    }

    fun getCollectorById(collectorId: Int): CollectorDto? {
        return collectorsCache.find { it.id == collectorId }
    }
}