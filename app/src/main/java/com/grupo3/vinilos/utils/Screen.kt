package com.grupo3.vinilos.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.grupo3.vinilos.R

sealed class Screen(val route: String,  val label: String, val icon: Int ) {
    object Albums : Screen("albums", "Albumes", R.drawable.album);
    object Artists: Screen("artists", "Artistas", R.drawable.artist);
    object Collectors: Screen("collectors", "Coleccionistas", R.drawable.collector);
}