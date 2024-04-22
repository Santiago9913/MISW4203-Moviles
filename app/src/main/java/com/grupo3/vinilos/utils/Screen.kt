package com.grupo3.vinilos.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String,  val label: String, val icon: ImageVector) {
    object Albums : Screen("albums", "Albumes", Icons.Filled.Favorite);
    object Artists: Screen("artists", "Artistas", Icons.Filled.Add);
}