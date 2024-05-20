package com.grupo3.vinilos.collector.service

import com.grupo3.vinilos.collector.dto.CollectorDto
import com.grupo3.vinilos.network.RetroFitInstance

class CollectorRepository {
    private val collectorService = RetroFitInstance.collectorService

    suspend fun getCollectors(): List<CollectorDto> {
        return collectorService.getCollectors()
    }

    suspend fun getCollector(id: Int): CollectorDto {
        return collectorService.getCollector(id)
    }
}