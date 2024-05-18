package com.grupo3.vinilos.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.grupo3.vinilos.album.detail.AlbumDetail
import com.grupo3.vinilos.album.songs.SongsList
import com.grupo3.vinilos.album.list.AlbumList
import com.grupo3.vinilos.album.songs.AddSong
import com.grupo3.vinilos.artists.detail.ArtistDetail
import com.grupo3.vinilos.artists.list.ArtistList
import com.grupo3.vinilos.collector.list.CollectorList
import com.grupo3.vinilos.ui.theme.Primary
import com.grupo3.vinilos.utils.Screen
import com.grupo3.vinilos.utils.navigateToWithState


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
            NavigationBar(
                containerColor = Primary.copy(alpha = 0.3f)
            ) {
                val navBackStackEntry by innerNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Primary.copy(alpha = 0.5f),
                            indicatorColor = Primary.copy(alpha = 0.5f)
                        ),
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        label = {
                            Text(text = screen.label)
                        },
                        onClick = {
                            navigateToWithState(screen.route, innerNavController)
                        },
                        icon = {
                            Image(
                                painter = painterResource(id = screen.icon!!),
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp)
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            innerNavController,
            startDestination = Screen.Albums.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Albums.route) {
                AlbumList(navigateTo = { route: String ->
                    navigateToWithState(
                        route,
                        innerNavController
                    )
                })
            }
            composable(Screen.Artists.route) {
                ArtistList(navigateTo = { route: String ->
                    navigateToWithState(
                        route,
                        innerNavController
                    )
                })
            }
            composable(Screen.Collectors.route) { CollectorList() }
            composable(Screen.ArtistDetail.route) { ArtistDetail(artistId = it.arguments?.getString("artistId")) }
            composable(
                Screen.AlbumDetail.route,
                arguments = listOf(navArgument("albumId") { type = NavType.StringType })
            ) { backStackentry ->
                AlbumDetail(navigateTo = { route: String ->
                    navigateToWithState(
                        route,
                        innerNavController,
                    )
                }, albumId = backStackentry.arguments?.getString("albumId"))
            }
            composable(
                Screen.SongList.route,
                arguments = listOf(navArgument("albumId") { type = NavType.StringType })
            ) { backStackEntry ->
                SongsList(navigateTo = { route: String ->
                    navigateToWithState(
                        route,
                        innerNavController,
                    )
                }, albumId = backStackEntry.arguments?.getString("albumId"))
            }
            composable(
                Screen.AddSong.route,
                arguments = listOf(navArgument("albumId") { type = NavType.StringType })
            ) { backStackEntry ->
                AddSong(navigateTo = { route: String ->
                    navigateToWithState(
                        route,
                        innerNavController,
                    )
                }, albumId = backStackEntry.arguments?.getString("albumId"))
            }
        }
    }
}