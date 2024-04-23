package com.grupo3.vinilos.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.grupo3.vinilos.album.list.AlbumList
import com.grupo3.vinilos.artist.list.ArtistList
import com.grupo3.vinilos.collector.list.CollectorList
import com.grupo3.vinilos.utils.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    auth: Boolean?
) {
    val items = listOf(
        Screen.Albums,
        Screen.Artists,
        Screen.Collectors,
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
                    label = {
                            Text(text = screen.label)
                    },
                    onClick = {
                              innerNavController.navigate(screen.route) {
                                  popUpTo(innerNavController.graph.startDestinationId) {
                                      saveState = true;
                                  }
                                  launchSingleTop = true;
                                  restoreState = true;

                              }
                    },
                    icon = { Image(painter = painterResource(id = screen.icon), contentDescription = "",
                        contentScale = ContentScale.Fit, modifier = Modifier
                            .width(24.dp)
                            .height(24.dp) )
                           },
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(innerNavController, startDestination = Screen.Albums.route, Modifier.padding(innerPadding)) {
            composable(Screen.Albums.route) { AlbumList() }
            composable(Screen.Artists.route) { ArtistList() }
            composable(Screen.Collectors.route) { CollectorList() }
        }
    }
}