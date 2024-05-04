package com.grupo3.vinilos.collector.service

import com.grupo3.vinilos.collector.dto.CollectorDto
import retrofit2.http.GET

interface CollectorService {
    @GET("collectors")
    suspend fun getCollectors(): List<CollectorDto>
}