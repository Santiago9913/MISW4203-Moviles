package com.grupo3.vinilos.album.register

data class AlbumsRegistrationState (
    val errorMessage: String? = null,
    val succeedMessage: String? = null,
    var loading:Boolean = false
)