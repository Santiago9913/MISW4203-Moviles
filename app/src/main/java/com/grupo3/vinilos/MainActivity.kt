package com.grupo3.vinilos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.grupo3.vinilos.home.HomeScreen
import com.grupo3.vinilos.ui.theme.VinilosTheme
import com.grupo3.vinilos.home.HomeScreen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VinilosTheme {
               val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "LogIn") {
                    composable("LogIn") { LogInScreen(
                        navController
                    ) }
                    composable("Home?auth={auth}", arguments = listOf(
                        navArgument("auth") {defaultValue = false}
                    )) { backStackEntry -> HomeScreen(
                        navController, backStackEntry.arguments?.getBoolean("auth"))
                    }
                }
            }
        }
    }
}