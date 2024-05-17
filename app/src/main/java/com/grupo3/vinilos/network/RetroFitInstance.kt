package com.grupo3.vinilos.network

import com.google.gson.GsonBuilder
import com.grupo3.vinilos.album.service.AlbumService
import com.grupo3.vinilos.artists.service.ArtistsService
import com.grupo3.vinilos.collector.service.CollectorService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitInstance {

    private const val BASE_URL = "https://backvynilsmiso-vnt7ed7xsq-uc.a.run.app/"

    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .create()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val artistsService: ArtistsService by lazy {
        retrofit.create(ArtistsService::class.java)
    }

    val albumsService: AlbumService by lazy {
        retrofit.create(AlbumService::class.java)
    }

    val collectorService: CollectorService by lazy {
        retrofit.create(CollectorService::class.java)
    }

}