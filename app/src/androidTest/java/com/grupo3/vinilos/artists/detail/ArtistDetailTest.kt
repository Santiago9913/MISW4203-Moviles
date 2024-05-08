package com.grupo3.vinilos.artists.detail

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import androidx.navigation.testing.TestNavHostController
import com.grupo3.vinilos.LogInScreen
import com.grupo3.vinilos.artists.dto.ArtistDto
import com.grupo3.vinilos.home.HomeScreen
import com.grupo3.vinilos.utils.parseDateToDDMMYYYY
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

class ArtistDetailTest {

    @get:Rule
    var rule = createComposeRule()
    lateinit var navController: TestNavHostController;
    lateinit var visitanteRoleName: String;
    lateinit var artistsNavBar: String;
    lateinit var artist: ArtistDto;
    lateinit var fecha: String;
    lateinit var artistImageTag: String;

    @Before
    fun setupAppHost() {
        rule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator((ComposeNavigator()))
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
        artistsNavBar = "Artistas"
        artist = ArtistDto(
            id = 0,
            name = "Rubén Blades Bellido de Luna",
            description = "Es un cantante, compositor, músico, actor, abogado, político y activista panameño. Ha desarrollado gran parte de su carrera artística en la ciudad de Nueva York.",
            birthDate = Date("1948/7/16"),
            image = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Ruben_Blades_by_Gage_Skidmore.jpg/800px-Ruben_Blades_by_Gage_Skidmore.jpg",
            creationDate = null
        )
        fecha = "Fecha de nacimiento: ${artist.birthDate?.let { parseDateToDDMMYYYY(it) }}"
        artistImageTag = "artistImage_${artist.image}"
    }

    @Test
    fun asVisitante_showArtistDetail() {
        rule.onNodeWithText(visitanteRoleName).assertExists().performClick()
        rule.onNodeWithText(artistsNavBar).assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(artist.name).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithText(artist.name).assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(artist.name).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithText(artist.name).assertExists()
        rule.onNodeWithText(fecha).assertExists()
        rule.onNodeWithText(artist.description).assertExists()
        rule.onNodeWithTag(artistImageTag).assertExists()
    }
}