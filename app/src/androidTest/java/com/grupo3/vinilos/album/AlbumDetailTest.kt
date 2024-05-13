package com.grupo3.vinilos.album

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
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

class AlbumDetailTest {

    @get:Rule
    var rule = createComposeRule()
    lateinit var navController: TestNavHostController
    lateinit var visitanteRoleName:String
    lateinit var album:AlbumDto
    lateinit var fechaDeLanzamiento: String
    lateinit var albumCoverTag:String

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

        visitanteRoleName = "Visitante"
        album = AlbumDto(id = 0, name = "Buscando América", description = "Buscando América es el primer álbum de la banda de Rubén Blades y Seis del Solar lanzado en 1984. La producción, bajo el sello Elektra, fusiona diferentes ritmos musicales tales como la salsa, reggae, rock, y el jazz latino. El disco fue grabado en Eurosound Studios en Nueva York entre mayo y agosto de 1983.", releaseDate = Date("1984/8/1"), cover = "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg", genre = "Salsa", recordLabel = "Elektra")
        fechaDeLanzamiento = "Fecha de lanzamiento: ${parseDateToDDMMYYYY(album.releaseDate)}"
        albumCoverTag = "albumImage_${album.cover}"
    }

    @Test
    fun asVisitante_showAlbumDetail(){
        rule.onNodeWithText(visitanteRoleName).assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(album.name).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithText(album.name).assertExists().performClick()
        rule.waitUntil(4000L) {
            rule.onAllNodesWithText(album.name).fetchSemanticsNodes().size == 1
        }
        rule.onNodeWithText(album.name).assertExists()
        rule.onNodeWithText(fechaDeLanzamiento).assertExists()
        rule.onNodeWithText(album.description).assertExists()
        rule.onNodeWithText(album.genre).assertExists()
        rule.onNodeWithText(album.recordLabel).assertExists()
        rule.onNodeWithTag(albumCoverTag).assertExists()
    }
}