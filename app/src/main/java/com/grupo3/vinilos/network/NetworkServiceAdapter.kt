package com.grupo3.vinilos.network

import com.grupo3.vinilos.model.Band
import com.grupo3.vinilos.model.Musician


class NetworkServiceAdapter {
    companion object {
        suspend fun getBands(): List<Band> {
            val response = RetrofitApi.retrofitService.getBands()
            if (response.isSuccessful) {
                return response.body() ?: emptyList()
            } else {
                throw Exception("Error: ${response.code()}")
            }
        }

        suspend fun getMusicians(): List<Musician> {
            val response = RetrofitApi.retrofitService.getMusicians()
            if (response.isSuccessful) {
                return response.body() ?: emptyList()
            } else {
                throw Exception("Error: ${response.code()}")
            }
        }
    }
}
