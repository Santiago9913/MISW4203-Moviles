package com.grupo3.vinilos.utils

import com.grupo3.vinilos.R

sealed class Screen(val route: String, val label: String, val icon: Int?) {
    data object Albums : Screen("albums", "Albumes", R.drawable.album)
    data object Artists : Screen("artists", "Artistas", R.drawable.artist)
    data object Collectors : Screen("collectors", "Coleccionistas", R.drawable.collector)
    data object CollectorDetail : Screen("collectors/{collectorId}", "ColeccionistaDetalle", null)
    data object ArtistDetail : Screen("artists/{artistId}", "ArtistaDetalle", null)
    data object AlbumDetail : Screen("albums/{albumId}", "AlbumDetalle", null)
    data object SongList : Screen("{albumId}/songs", label = "SongsList", icon = null)
    data object AddSong : Screen("{albumId}/songs/add", label = "AddSong", icon = null)
}