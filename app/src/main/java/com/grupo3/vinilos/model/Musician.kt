package com.grupo3.vinilos.model

import java.util.Date

data class Musician(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: Date
)
