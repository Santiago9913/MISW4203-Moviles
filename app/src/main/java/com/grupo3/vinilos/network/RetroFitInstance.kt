package com.grupo3.vinilos.network

import com.grupo3.vinilos.artists.service.ArtistsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitInstance {
    private const val BASE_URL = "https://backvynilsmiso-vnt7ed7xsq-uc.a.run.app/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val artistsService: ArtistsService by lazy {
        retrofit.create(ArtistsService::class.java)
    }
}