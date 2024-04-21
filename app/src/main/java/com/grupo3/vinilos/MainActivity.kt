package com.grupo3.vinilos

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.grupo3.vinilos.ui.theme.VinilosTheme

class MainActivity : ComponentActivity() {

    sealed class Screen (val route:String){
        object AlbumsScreen : Screen("albums")
        object ArtistsScreen : Screen("artists")
        object CollectorsScreen : Screen("collectors")
    }

    @Composable
    fun AlbumsScreen(navController: NavController){
        Text(text = "Albums")
    }

    @Composable
    fun ArtistsScreen(navController: NavController){
        Text(text = "Artists")
    }

    @Composable
    fun CollectorsScreen(navController: NavController){
        Text(text = "Collectors")
    }

    @Composable
    fun Navigation(navController:NavHostController){
        NavHost(
            navController = navController,
            startDestination = Screen.AlbumsScreen.route,
        ){
            composable(route = Screen.AlbumsScreen.route) {
                AlbumsScreen(navController = navController)
            }
            composable(route = Screen.ArtistsScreen.route) {
                ArtistsScreen(navController = navController)
            }
            composable(route = Screen.CollectorsScreen.route) {
                CollectorsScreen(navController = navController)
            }
        }
    }

    sealed class BottomNavItem(
        var title: String,
        var icon: Int,
        var url: String
    ) {
        object Albums :
            BottomNavItem(
                "Albums",
                R.drawable.album_icons,
                url = Screen.AlbumsScreen.route
            )

        object Artistas :
            BottomNavItem(
                "Artistas",
                R.drawable.artistas_icons,
                url = Screen.ArtistsScreen.route
            )

        object Coleccionistas:
            BottomNavItem(
                "Coleccionistas",
                R.drawable.airpods_icons,
                url = Screen.CollectorsScreen.route
            )
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VinilosTheme (dynamicColor = false) {
                var navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigation(navController = navController)
                    },
                ) {
                    Navigation(navController)
            }
        }
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Albums,
        BottomNavItem.Artistas,
        BottomNavItem.Coleccionistas
    )
    NavigationBar (   containerColor = MaterialTheme.colorScheme.onSurface, contentColor = MaterialTheme.colorScheme.onSecondary) {
        items.forEach { item ->
            AddItem(
                screen = item,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    navController: NavController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title, color=MaterialTheme.colorScheme.onPrimary)
        },
        icon = {
            Icon(
                painterResource(id = screen.icon),
                contentDescription = screen.title
            )
        },
        selected = true,
        alwaysShowLabel = true,
        onClick = {
            navController.navigate(screen.url )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.onTertiary,
            selectedTextColor = MaterialTheme.colorScheme.onTertiary,
            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
            indicatorColor = MaterialTheme.colorScheme.onSurface
        )
    )
}}