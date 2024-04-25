package com.grupo3.vinilos.repository

import com.grupo3.vinilos.network.NetworkServiceAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class ArtistRepository() {
    suspend fun refreshData(): List<Any> = withContext(Dispatchers.IO) {
        val bandsDeferred = async { NetworkServiceAdapter.getBands() }
        val musiciansDeferred = async { NetworkServiceAdapter.getMusicians() }

        val bands = bandsDeferred.await()
        val musicians = musiciansDeferred.await()

        bands + musicians
    }
}