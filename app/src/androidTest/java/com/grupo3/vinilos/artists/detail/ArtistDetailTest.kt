package com.grupo3.vinilos.artists.detail

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import androidx.navigation.testing.TestNavHostController
import com.grupo3.vinilos.LogInScreen
import com.grupo3.vinilos.home.HomeScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtistDetailTest {

    @get:Rule
    var rule = createComposeRule()
    lateinit var navController: TestNavHostController;

    @Before
    fun setupAppHost(){
        rule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator((ComposeNavigator()))
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
    @Test
    fun loginAsVisitante_showsNavigationBar(){
        rule.onNodeWithText("Visitante").assertExists()
        /* rule.onNode(hasText("Visitante")).performClick()
        rule.onNode(hasText("Artistas")).assertExists()
        rule.onNode(hasText("Albumes")).assertExists()
        rule.onNode(hasText("Coleccionistas")).assertExists()*/
    }
}