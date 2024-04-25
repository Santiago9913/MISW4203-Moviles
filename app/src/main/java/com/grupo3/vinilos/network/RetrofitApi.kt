package com.grupo3.vinilos.network

import com.grupo3.vinilos.model.Band
import com.grupo3.vinilos.model.Musician
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://backvynilsmiso-vnt7ed7xsq-uc.a.run.app/"

val gson = GsonBuilder()
    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    .create()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

interface RetrofitApiService {
    @GET("bands")
    suspend fun getBands():
            Response<List<Band>>

    @GET("bands")
    suspend fun getMusicians():
            Response<List<Musician>>
}

object RetrofitApi {
    val retrofitService: RetrofitApiService by lazy {
        retrofit.create(RetrofitApiService::class.java)
    }
}