package com.grupo3.vinilos.collector.service

import android.util.Log
import com.grupo3.vinilos.collector.dto.CollectorDto
import com.grupo3.vinilos.network.CacheManager
import com.grupo3.vinilos.network.RetroFitInstance

class CollectorRepository {
    private val collectorService = RetroFitInstance.collectorService

    suspend fun getCollectors(): List<CollectorDto> {
        val potentialCollectors = CacheManager.getInstance().getCollectors()
        return if (potentialCollectors.isEmpty()) {
            Log.d("Cache decision", "getting collectors from network")
            val collectors = collectorService.getCollectors()
            CacheManager.getInstance().addCollectors(collectors)
            collectors
        } else {
            Log.d("Cache decision", "return ${potentialCollectors.size} elements from cache")
            potentialCollectors
        }
    }

    suspend fun getCollector(id: Int): CollectorDto {
        val potentialCollector = CacheManager.getInstance().getCollectorById(id)
        return if (potentialCollector == null) {
            Log.d("Cache decision", "collectorId: $id - getting collector from network")
            val collector = collectorService.getCollector(id)
            CacheManager.getInstance().addCollectors(listOf(collector))
            collector
        } else {
            Log.d("Cache decision", "collectorId: $id - return element from cache")
            potentialCollector
        }
    }
}