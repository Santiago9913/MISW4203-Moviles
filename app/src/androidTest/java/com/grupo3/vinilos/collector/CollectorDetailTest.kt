package com.grupo3.vinilos.collector

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
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

class CollectorDetailTest {

    @get:Rule
    var rule = createComposeRule()
    lateinit var navController: TestNavHostController
    lateinit var visitanteRoleName: String
    lateinit var collectorsNavBar: String
    lateinit var collectorName: String
    lateinit var collectorTelephone: String
    lateinit var collectorEmail: String
    lateinit var collectorDetailTitle: String

    @Before
    fun setupAppHost() {
        rule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NavHost(navController = navController, startDestination = "LogIn") {
                composable("LogIn") {
                    LogInScreen(
                        navController
                    )
                }
                composable("Home?auth={auth}", arguments = listOf(
                    navArgument("auth") { defaultValue = false }
                )) { backStackEntry ->
                    HomeScreen(
                        navController, backStackEntry.arguments?.getBoolean("auth")
                    )
                }
            }
        }

        visitanteRoleName = "Visitante"
        collectorsNavBar = "Coleccionistas"
        collectorName = "Manolo Bellon"
        collectorTelephone = "3502457896"
        collectorEmail = "manollo@caracol.com.co"
        collectorDetailTitle = "Coleccionista"
    }

    @Test
    fun asVisitante_showCollectorDetail() {
        rule.onNodeWithText(visitanteRoleName).assertExists().performClick()
        rule.onNodeWithText(collectorsNavBar).assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(collectorName).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithText(collectorName).assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(collectorDetailTitle).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithText(collectorDetailTitle).assertExists()
        rule.onNodeWithText(collectorName).assertExists()
        rule.onNodeWithText(collectorTelephone).assertExists()
        rule.onNodeWithText(collectorEmail).assertExists()
    }
}
