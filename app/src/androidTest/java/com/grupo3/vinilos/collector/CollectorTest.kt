package com.grupo3.vinilos.collector

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.testing.TestNavHostController
import com.grupo3.vinilos.LogInScreen
import com.grupo3.vinilos.home.HomeScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CollectorTest {
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
    fun asVisitante_showListOfCollectors(){
        val collectorTitle ="Buscando América"
        rule.onNodeWithText("Visitante").assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(collectorTitle).fetchSemanticsNodes().size == 1
        }
    }

    @Test
    fun asColeccionista_showListOfCollectors(){
        val collectorTitle ="Buscando América"
        rule.onNodeWithText("Coleccionista").assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(collectorTitle).fetchSemanticsNodes().size == 1
        }
    }
}