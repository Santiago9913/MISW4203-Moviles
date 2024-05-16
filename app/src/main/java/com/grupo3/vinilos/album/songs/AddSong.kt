package com.grupo3.vinilos.album.songs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.grupo3.vinilos.album.detail.AlbumDetailViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grupo3.vinilos.album.dto.SongCreateDto

@Composable
fun AddSong(
    navigateTo: (String) -> Unit,
    viewModel: AlbumDetailViewModel = viewModel(),
    albumId: String?
) {
    var songName by remember { mutableStateOf("") }
    var songDuration by remember { mutableStateOf("") }

    Column {
        Text("Add a new song")
        TextField(
            value = songName,
            onValueChange = { songName = it },
            label = { Text("Song Name") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
        )
        TextField(
            value = songDuration,
            onValueChange = { songDuration = it },
            label = { Text("Song Duration") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { /* Handle done action */ })
        )
        Button(onClick = {
            if (albumId != null) {
                viewModel.addSong(albumId.toInt(), SongCreateDto(songName, songDuration))
            }
        }) {
            Text("Add Song")
        }
    }
}