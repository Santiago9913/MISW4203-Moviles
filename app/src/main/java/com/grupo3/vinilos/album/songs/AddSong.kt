package com.grupo3.vinilos.album.songs

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grupo3.vinilos.R
import com.grupo3.vinilos.album.detail.AlbumDetailViewModel
import com.grupo3.vinilos.album.dto.SongCreateDto
import com.grupo3.vinilos.ui.theme.Accent
import com.grupo3.vinilos.ui.theme.UiPadding
import com.grupo3.vinilos.utils.FORMAT_DURATION
import com.grupo3.vinilos.utils.Screen
import com.grupo3.vinilos.utils.isValidSongDuration

@Composable
fun AddSong(
    navigateTo: (String) -> Unit, viewModel: AlbumDetailViewModel = viewModel(), albumId: String?
) {
    var songName by remember { mutableStateOf("") }
    var songDuration by remember { mutableStateOf("") }
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.songs.size) {
        if (state.songs.isNotEmpty()) {
            Toast.makeText(context, R.string.song_successfully_added_label, Toast.LENGTH_LONG)
                .show()
            navigateTo(Screen.SongList.route.replace("{albumId}", albumId!!))
        }
    }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        Modifier
            .padding(
                start = UiPadding.large,
                top = UiPadding.medium,
                end = UiPadding.large,
                bottom = UiPadding.xxLarge
            )
            .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            OutlinedTextField(
                value = songName,
                onValueChange = { songName = it },
                label = { Text(stringResource(id = R.string.input_song_name_label)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("song_name_input")
            )
            OutlinedTextField(value = songDuration,
                onValueChange = { songDuration = it },
                label = { Text(stringResource(id = R.string.input_song_duration_label)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("song_duration_input"),
                placeholder = { Text(text = FORMAT_DURATION) })
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("save_song_button"),
            onClick = {
                if (songName.isBlank() || songDuration.isBlank()) {
                    Toast.makeText(context, R.string.song_name_duration_error, Toast.LENGTH_SHORT)
                        .show()
                } else if (albumId != null && isValidSongDuration(songDuration)) {
                    viewModel.addSong(albumId.toInt(), SongCreateDto(songName, songDuration))
                } else {
                    Toast.makeText(context, R.string.song_duration_format_error, Toast.LENGTH_SHORT)
                        .show()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Accent,
            ),
        ) {
            Text(stringResource(id = R.string.save_song_button))
        }
    }
}