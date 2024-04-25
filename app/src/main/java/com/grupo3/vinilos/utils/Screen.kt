package com.grupo3.vinilos.utils

import com.grupo3.vinilos.R

sealed class Screen(val route: String, val label: String, val icon: Int) {
    data object Albums : Screen("albums", "Albumes", R.drawable.album)
    data object Artists : Screen("artists", "Artistas", R.drawable.artist)
    data object Collectors : Screen("collectors", "Coleccionistas", R.drawable.collector)
}