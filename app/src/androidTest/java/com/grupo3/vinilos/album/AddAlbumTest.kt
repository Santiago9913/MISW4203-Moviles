package com.grupo3.vinilos.album

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
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

class AddAlbumTest {

    @get:Rule
    var rule = createComposeRule()
    lateinit var navController: TestNavHostController
    lateinit var coleccionistaRoleName: String
    lateinit var visitanteRoleName:String
    lateinit var addAlbumFAB: String
    lateinit var albumNameTag: String
    lateinit var albumCoverTag: String
    lateinit var albumDescriptionTag: String
    lateinit var albumReleaseDateTag: String
    lateinit var albumGenresTag: String
    lateinit var albumRecordLabelsTag: String
    lateinit var albumRegistrationButtomName: String

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

        coleccionistaRoleName = "Coleccionista"
        visitanteRoleName = "Visitante"
        albumNameTag = "album_name_field"
        albumCoverTag = "album_cover_field"
        albumReleaseDateTag = "album_releaseDate_field"
        albumGenresTag = "album_genres_field"
        albumRecordLabelsTag = "album_recordlabels_field"
        albumRegistrationButtomName = "Registrar"
        addAlbumFAB = "album_registration_fab"
    }

    @Test
    fun asCollector_shouldBeAbleToGoToAlbumRegistration(){
        rule.onNodeWithText(coleccionistaRoleName).assertExists().performClick()
        rule.onNodeWithTag(addAlbumFAB).assertExists().performClick()
        rule.onNodeWithTag(albumNameTag).assertExists()
        rule.onNodeWithTag(albumCoverTag).assertExists()
        rule.onNodeWithTag(albumReleaseDateTag).assertExists()
        rule.onNodeWithTag(albumGenresTag).assertExists()
        rule.onNodeWithTag(albumRecordLabelsTag).assertExists()
        rule.onNodeWithText(albumRegistrationButtomName).assertExists()
    }

    @Test
    fun asVisitor_shouldNotBeAbleToGoToAlbumRegistration(){
        rule.onNodeWithText(visitanteRoleName).assertExists().performClick()
        rule.onNodeWithTag(addAlbumFAB).assertDoesNotExist()
    }

}