package com.grupo3.vinilos.album.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.grupo3.vinilos.R
import com.grupo3.vinilos.artists.list.ArtistsViewModel
import com.grupo3.vinilos.ui.theme.Accent
import com.grupo3.vinilos.ui.theme.Typography
import com.grupo3.vinilos.ui.theme.UiPadding
import com.grupo3.vinilos.utils.Screen
import com.grupo3.vinilos.utils.parseDateToDDMMYYYY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetail(
    viewModel: AlbumDetailViewModel = viewModel(),
    navigateTo: (String) -> Unit,
    albumId: String?
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        if (albumId != null) {
            println("Album id: ${albumId}")
            viewModel.getAlbumDetail(albumId.toInt());
        }
    }

    if (state.album == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.album_not_available),
                style = Typography.titleMedium
            )
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        top = UiPadding.medium,
                        start = UiPadding.medium,
                        end = UiPadding.medium,
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    AsyncImage(
                        model = state.album!!.cover,
                        modifier = Modifier
                            .height(300.dp)
                            .width(300.dp)
                            .padding(
                                top = UiPadding.medium,
                                bottom = UiPadding.medium,
                            )
                            .testTag("albumImage_" + state.album!!.cover),
                        contentDescription = stringResource(id = R.string.albums_not_available)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = UiPadding.medium,
                            bottom = UiPadding.medium,
                        ),
                    horizontalArrangement = Arrangement.Center,

                    ) {
                    Text(
                        text = state.album!!.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = UiPadding.medium,
                            bottom = UiPadding.medium,
                        ),
                ) {
                    Text(
                        "${stringResource(id = R.string.album_fecha_lanzamiento_label)}: ${
                            parseDateToDDMMYYYY(
                                state.album!!.releaseDate
                            )
                        }",
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 2
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = UiPadding.medium,
                            bottom = UiPadding.medium,
                        ),
                ) {
                    Text(
                        state.album!!.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = UiPadding.medium,
                            bottom = UiPadding.medium,
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    SuggestionChip(
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            labelColor = Color.White,
                            containerColor = MaterialTheme.colorScheme.secondary,
                            disabledContainerColor = MaterialTheme.colorScheme.secondary,
                            disabledLabelColor = Color.White
                        ),
                        modifier = Modifier.height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        onClick = {},
                        label = { Text(state.album!!.genre) },
                        enabled = false,
                        border = SuggestionChipDefaults.suggestionChipBorder(borderWidth = 0.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    SuggestionChip(
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            labelColor = Color.White,
                            containerColor = MaterialTheme.colorScheme.secondary,
                            disabledContainerColor = MaterialTheme.colorScheme.secondary,
                            disabledLabelColor = Color.White
                        ),
                        modifier = Modifier.height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        onClick = {},
                        enabled = false,
                        label = { Text(state.album!!.recordLabel) },
                        border = SuggestionChipDefaults.suggestionChipBorder(borderWidth = 0.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = UiPadding.medium,
                            bottom = UiPadding.medium,
                        ),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Button(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        onClick = {
                            val id = albumId ?: "0"
                            val route = Screen.SongList.route.replace("{albumId}", id)
                            navigateTo(route)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Accent,
                        ),
                    ) {
                        Text(text = "Canciones")
                    }
                }
            }
        }
    }
}