package com.grupo3.vinilos.collector.service

import com.grupo3.vinilos.collector.dto.CollectorDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectorService {
    @GET("collectors")
    suspend fun getCollectors(): List<CollectorDto>

    @GET("collectors/{id}")
    suspend fun getCollector(@Path("id") id: Int): CollectorDto
}