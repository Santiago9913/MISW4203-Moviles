package com.grupo3.vinilos.collector.detail

import com.grupo3.vinilos.collector.dto.CollectorDto

data class CollectorDetailState(
    val collector: CollectorDto = CollectorDto(0, "", "", ""),
    val errorMessage: String? = null
)