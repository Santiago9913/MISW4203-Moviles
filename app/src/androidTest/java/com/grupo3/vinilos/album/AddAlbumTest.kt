package com.grupo3.vinilos.album

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
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

class AddAlbumTest {

    @get:Rule
    var rule = createComposeRule()
    lateinit var navController: TestNavHostController
    lateinit var visitanteRoleName:String
    lateinit var addAlbumFAB: String
    lateinit var albumNameTag: String;
    lateinit var albumCoverTag: String;
    lateinit var albumDescriptionTag: String;
    lateinit var albumReleaseDateTag: String;
    lateinit var albumGenresTag: String;
    lateinit var albumRecordLabelsTag: String;
    lateinit var albumRegistrationButtomName: String;

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
        albumNameTag = "album_name_field"
        albumCoverTag = "album_cover_field"
        albumReleaseDateTag = "album_releaseDate_field"
        albumGenresTag = "album_genres_field"
        albumRecordLabelsTag = "album_recordlabels_field"
        albumRegistrationButtomName = "Registrar"
        addAlbumFAB = "album_registration_fab"
    }

    @Test
    fun asVisitante_shouldBeAbleToGoToAlbumRegistration(){
        rule.onNodeWithText(visitanteRoleName).assertExists().performClick()
        rule.onNodeWithTag(addAlbumFAB).assertExists().performClick()
        rule.onNodeWithTag(albumNameTag).assertExists()
        rule.onNodeWithTag(albumCoverTag).assertExists()
        rule.onNodeWithTag(albumReleaseDateTag).assertExists()
        rule.onNodeWithTag(albumGenresTag).assertExists()
        rule.onNodeWithTag(albumRecordLabelsTag).assertExists()
        rule.onNodeWithText(albumRegistrationButtomName).assertExists()
    }

}