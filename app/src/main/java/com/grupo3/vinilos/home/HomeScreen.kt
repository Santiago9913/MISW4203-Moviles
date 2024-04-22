package com.grupo3.vinilos.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.grupo3.vinilos.album.list.AlbumList
import com.grupo3.vinilos.artist.list.ArtistList
import com.grupo3.vinilos.utils.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    auth: Boolean?
) {
    val items = listOf(
        Screen.Albums,
        Screen.Artists
    )
    val innerNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by innerNavController.currentBackStackEntryAsState();
                val currentDestination = navBackStackEntry?.destination;

                items.forEach {
                    screen -> NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true,
                    onClick = {
                              innerNavController.navigate(screen.route) {
                                  popUpTo(navController.graph.findStartDestination().id) {
                                      saveState = true;
                                  }
                                  launchSingleTop = true;
                                  restoreState = true;
                              }
                    },
                    icon = { Icon(imageVector = screen.icon, contentDescription = "") }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(innerNavController, startDestination = Screen.Albums.route, Modifier.padding(innerPadding)) {
            composable(Screen.Albums.route) { AlbumList() }
            composable(Screen.Artists.route) { ArtistList() }
        }
    }
}