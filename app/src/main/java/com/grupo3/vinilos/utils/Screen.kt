package com.grupo3.vinilos.utils

import com.grupo3.vinilos.R

sealed class Screen(val route: String, val label: String, val icon: Int?, val contentDescription: String?) {
    data object Albums : Screen("albums", "Albumes", R.drawable.album, "Albumes")
    data object Artists : Screen("artists", "Artistas", R.drawable.artist, "Artistas")
    data object Collectors : Screen("collectors", "Coleccionistas", R.drawable.collector, "Coleccionistas")
    data object CollectorDetail : Screen("collectors/{collectorId}", "ColeccionistaDetalle", null, null)
    data object ArtistDetail : Screen("artists/{artistId}", "ArtistaDetalle", null, null)
    data object AlbumDetail : Screen("albums/{albumId}", "AlbumDetalle", null, null)
    data object SongList : Screen("{albumId}/songs", label = "SongsList", icon = null, null)
    data object AddSong : Screen("{albumId}/songs/add", label = "AddSong", icon = null, null)
    data object AddAlbum : Screen("albums/add", label = "AddAlbum", icon = R.drawable.white_pencil, null)
}