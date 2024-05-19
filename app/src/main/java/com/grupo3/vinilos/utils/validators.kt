package com.grupo3.vinilos.utils

fun isValidSongDuration(duration: String): Boolean {
    val pattern = "^([0-5]?[0-9]):([0-5]?[0-9])$".toRegex()
    return pattern.matches(duration)
}