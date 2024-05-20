package com.grupo3.vinilos.album.register

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.grupo3.vinilos.R
import com.grupo3.vinilos.album.detail.AlbumDetailViewModel
import com.grupo3.vinilos.ui.theme.Accent
import com.grupo3.vinilos.ui.theme.Typography
import com.grupo3.vinilos.ui.theme.UiPadding
import com.grupo3.vinilos.utils.Screen
import com.grupo3.vinilos.utils.parseDateToDDMMYYYY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.grupo3.vinilos.album.dto.AlbumDto
import com.grupo3.vinilos.album.dto.SongCreateDto
import com.grupo3.vinilos.album.list.AlbumsViewModel
import com.grupo3.vinilos.shared.DropDownList
import com.grupo3.vinilos.shared.Option
import com.grupo3.vinilos.utils.FORMAT_DURATION
import com.grupo3.vinilos.utils.convertMillisToLocalDateString
import com.grupo3.vinilos.utils.isValidSongDuration
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumRegistration(
    viewModel: AlbumsRegistrationViewModel = viewModel(),
    navigateTo: (String) -> Unit
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    var name by remember { mutableStateOf("") }
    var cover by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var recordLabel by remember { mutableStateOf("") }
    var fechaDeLanzamiento by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState()

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(state.succeddMessage) {
        state.succeddMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

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
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(id = R.string.album_registrar_nombre_album)) },
                    singleLine = true,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = cover,
                    onValueChange = { cover = it },
                    label = { Text(stringResource(id = R.string.album_registrar_cover_album)) },
                    singleLine = true

                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = {
                            showDialog = true
                        }),
                    singleLine = true,
                    value  = convertMillisToLocalDateString(dateState?.selectedDateMillis),
                    onValueChange = { },
                    readOnly = false,
                    label = { Text(stringResource(id = R.string.album_registrar_fecha_lanzamiento_album)) },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                showDialog = true
                            },
                        ) {
                            Icon(
                                Icons.Default.DateRange,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                )

                if (showDialog) {

                    DatePickerDialog(
                        onDismissRequest = { showDialog = false },
                        confirmButton = {
                            Button(
                                onClick = { showDialog = false; }
                            ) {
                                Text(text = "OK")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showDialog = false }
                            ) {
                                Text(text = "Cancel")
                            }
                        }
                    ) {
                        DatePicker(
                            state = dateState,
                            showModeToggle = true
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                val options = listOf(Option("Rock", "Rock"), Option("Salsa", "Salsa"))
                DropDownList(
                    title = stringResource(id = R.string.album_registrar_generos_modal_album),
                    label = stringResource(id = R.string.album_registrar_genero_album),
                    placeholder = stringResource(id = R.string.album_registrar_genero_album),
                    options =  options,
                    onClick = { option -> genero = option.label }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                val options = listOf(Option("Emi", "Emi"), Option("Elektra", "Elektra"))
                DropDownList(
                    title = stringResource(id = R.string.album_registrar_recordlabel_modal_album),
                    label = stringResource(id = R.string.album_registrar_recordlabel_album),
                    placeholder = stringResource(id = R.string.album_registrar_recordlabel_album),
                    options =  options,
                    onClick = { option -> recordLabel = option.label }
                )
            }
            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxHeight())
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,


                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("save_song_button"),
                    onClick = {
                          if (viewModel.isFormValid(name,cover,genero,recordLabel, convertMillisToLocalDateString(dateState?.selectedDateMillis))) {
                              val album = AlbumDto(name = name, cover = cover, genre = genero, releaseDate = Date("01/01/2001"), recordLabel = recordLabel, description = "", id = 0)
                              viewModel.createAlbum(album)
                          }
                        else {
                              Toast.makeText(context, R.string.song_name_duration_error, Toast.LENGTH_SHORT)
                                  .show()
                        }
                       /* if (songName.isBlank() || songDuration.isBlank()) {
                            Toast.makeText(context, R.string.song_name_duration_error, Toast.LENGTH_SHORT)
                                .show()
                        } else if (albumId != null && isValidSongDuration(songDuration)) {
                            viewModel.addSong(albumId.toInt(), SongCreateDto(songName, songDuration))
                        } else {
                            Toast.makeText(context, R.string.song_duration_format_error, Toast.LENGTH_SHORT)
                                .show()
                        } */
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Accent,
                    ),
                ) {
                    Text(stringResource(id = R.string.album_registrar_album))
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            }
        }

}