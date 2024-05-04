package com.grupo3.vinilos.collector.list

import com.grupo3.vinilos.collector.dto.CollectorDto

data class CollectorsListState(
    val collectors: List<CollectorDto> = emptyList(),
    val errorMessage: String? = null
)