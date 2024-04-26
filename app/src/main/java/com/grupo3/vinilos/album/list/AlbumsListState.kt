package com.grupo3.vinilos.album.list

import com.grupo3.vinilos.album.dto.AlbumDto

data class AlbumsListState (
    val albums: List<AlbumDto> = emptyList()

)
