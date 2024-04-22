package com.grupo3.vinilos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.grupo3.vinilos.ui.theme.VinilosTheme
import com.grupo3.vinilos.home.HomeScreen
class MainActivity : ComponentActivity() {

    private val LocalNavController = compositionLocalOf<NavController> { error("No NavController found") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VinilosTheme {
               val navController = rememberNavController();
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VinilosTheme {
        Greeting("Android")
    }
}