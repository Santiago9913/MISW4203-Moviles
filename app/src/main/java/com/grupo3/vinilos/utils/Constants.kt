package com.grupo3.vinilos.utils

import com.grupo3.vinilos.shared.Option

const val ERROR_MESSAGE = "Algo salió mal. Intente de nuevo..."
const val ES_VISITANTE = "es_visitante"
const val USER_PREFS = "user_preferences"
const val FORMAT_DURATION = "mm:ss"
const val SNACKBAR_SUCCESS = "Info"
const val SNACKBAR_ERROR = "Danger"
const val ALBUM_REGISTRADO_EXITOSAMENTE = "Álbum registrado exitosamente"
const val ALBUM_CAMPOS_INVALIDOS = "Nombre, Cover, Descripción, Género y Sello discográfico son obligatorios"

val generos = listOf(
    Option("Rock", "Rock"), Option("Salsa", "Salsa"),
    Option("Classical", "Classical"), Option("Folk", "Folk")
)

val selloDiscograficos = listOf(
    Option("Sony Music", "Sony Music"), Option("EMI", "EMI"),
    Option("Discos Fuentes", "Discos Fuentes"), Option("Elektra", "Elektra"),
    Option("Fania Records", "Fania Records")
)