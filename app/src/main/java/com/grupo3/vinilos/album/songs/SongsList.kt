package com.grupo3.vinilos.album.songs

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grupo3.vinilos.R
import com.grupo3.vinilos.album.detail.AlbumDetailViewModel
import com.grupo3.vinilos.ui.theme.Typography
import com.grupo3.vinilos.ui.theme.UiPadding
import com.grupo3.vinilos.ui.theme.background
import com.grupo3.vinilos.utils.ES_VISITANTE
import com.grupo3.vinilos.utils.Screen
import com.grupo3.vinilos.utils.USER_PREFS

@Composable
fun SongsList(
    navigateTo: (String) -> Unit,
    viewModel: AlbumDetailViewModel = viewModel(),
    albumId: String?
) {

    // Read the value from SharedPreferences
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE)
    val showButton = sharedPreferences.getBoolean(ES_VISITANTE, true)


    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        if (albumId != null) {
            viewModel.getSongs(albumId.toInt())
        }
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.songs.isEmpty()) {
            Text(
                text = stringResource(id = R.string.songs_not_available),
                style = Typography.titleMedium
            )
        } else {
            LazyColumn(
                Modifier
                    .padding(
                        top = UiPadding.medium,
                        start = UiPadding.large,
                        end = UiPadding.large,
                    )
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                items(state.songs) { song ->
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(bottom = UiPadding.large)
                            .background(
                                color = Color(background),
                            )
                    ) {
                        Column(
                            Modifier.padding(
                                start = UiPadding.large,
                                top = UiPadding.medium,
                            )
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = UiPadding.medium)
                            ) {
                                Column {
                                    Text(
                                        text = song.name, style = Typography.titleMedium,
                                    )
                                    Text(
                                        text = song.duration,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        style = Typography.bodySmall,
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }

        if (!showButton) {
            FloatingActionButton(
                onClick = {
                    val id = albumId ?: "0"
                    val route = Screen.AddSong.route.replace("{albumId}", id)
                    navigateTo(route)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = stringResource(id = R.string.add_song_button)
                )
            }
        }

    }
}