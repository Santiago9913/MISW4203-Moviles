package com.grupo3.vinilos.album

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.testing.TestNavHostController
import com.grupo3.vinilos.LogInScreen
import com.grupo3.vinilos.album.dto.AlbumDto
import com.grupo3.vinilos.home.HomeScreen
import com.grupo3.vinilos.utils.parseDateToDDMMYYYY
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date
import kotlin.random.Random

class AddSongTest {

    @get:Rule
    var rule = createComposeRule()
    lateinit var navController: TestNavHostController
    lateinit var coleccionistaRoleName: String
    lateinit var visitanteRoleName: String
    lateinit var album: AlbumDto
    lateinit var fechaDeLanzamiento: String
    lateinit var albumCoverTag: String
    lateinit var canciones: String
    lateinit var add_cancion_button: String
    lateinit var cancion_name: String

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

        coleccionistaRoleName = "Coleccionista"
        visitanteRoleName = "Visitante"
        album = AlbumDto(
            id = 0,
            name = "Buscando América",
            description = "Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.",
            releaseDate = Date("1984/8/1"),
            cover = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            genre = "Salsa",
            recordLabel = "Elektra"
        )
        fechaDeLanzamiento = "Fecha de lanzamiento: ${parseDateToDDMMYYYY(album.releaseDate)}"
        albumCoverTag = "albumImage_${album.cover}"
        cancion_name = "Decisiones"
        canciones = "Canciones"
        add_cancion_button = "Añadir canción"

    }

    @Test
    fun asColeccionista_canSeeTheAddButton() {
        rule.onNodeWithText(coleccionistaRoleName).assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(album.name).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithText(album.name).assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(album.name).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithText(album.name).assertExists()
        rule.onNodeWithText(canciones).performScrollTo().assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(cancion_name).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithContentDescription(add_cancion_button).assertExists()
    }

    @Test
    fun asColeccionista_canAddASong() {
        rule.onNodeWithText(coleccionistaRoleName).assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(album.name).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithText(album.name).assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(album.name).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithText(album.name).assertExists()
        rule.onNodeWithText(canciones).performScrollTo().assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(cancion_name).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithContentDescription(add_cancion_button).assertExists().performClick()

        val songName = "Mi canción " + Random.nextInt(1, 1001)
        rule.onNodeWithTag("song_name_input").assertExists().performTextInput(songName)
        rule.onNodeWithTag("song_duration_input").assertExists().performTextInput("04:20")
        rule.onNodeWithTag("save_song_button").assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(songName).fetchSemanticsNodes().isNotEmpty()
        }
        rule.onNodeWithText(songName).assertExists()
    }

    @Test
    fun asVisitante_cannotSeeTheAddButton() {
        rule.onNodeWithText(visitanteRoleName).assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(album.name).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithText(album.name).assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(album.name).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithText(album.name).assertExists()
        rule.onNodeWithText(canciones).performScrollTo().assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(cancion_name).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithContentDescription(add_cancion_button).assertDoesNotExist()
    }
}