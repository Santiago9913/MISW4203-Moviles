package com.grupo3.vinilos.album.register

import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grupo3.vinilos.R
import com.grupo3.vinilos.ui.theme.Accent
import com.grupo3.vinilos.ui.theme.UiPadding
import androidx.compose.runtime.setValue
import com.grupo3.vinilos.album.dto.AlbumRegistrationDto
import com.grupo3.vinilos.shared.DropDownList
import com.grupo3.vinilos.shared.Option
import com.grupo3.vinilos.utils.ALBUM_CAMPOS_INVALIDOS
import com.grupo3.vinilos.utils.ALBUM_REGISTRADO_EXITOSAMENTE
import com.grupo3.vinilos.utils.ERROR_MESSAGE
import com.grupo3.vinilos.utils.SNACKBAR_ERROR
import com.grupo3.vinilos.utils.SNACKBAR_SUCCESS
import com.grupo3.vinilos.utils.Screen
import com.grupo3.vinilos.utils.convertMillisToLocalDateString
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumRegistration(
    viewModel: AlbumsRegistrationViewModel = viewModel(),
    navigateTo: (String) -> Unit,
    showMessage: (type:String?, message: String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    var name by remember { mutableStateOf("") }
    var cover by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var recordLabel by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState()

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            showMessage(SNACKBAR_ERROR, ERROR_MESSAGE)
        }
    }

    LaunchedEffect(state.succeddMessage) {
        state.succeddMessage?.let {
            showMessage(SNACKBAR_SUCCESS, ALBUM_REGISTRADO_EXITOSAMENTE)
            navigateTo(Screen.Albums.route)
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
                    modifier = Modifier.fillMaxWidth(),
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text(stringResource(id = R.string.album_registrar_descripcion_album)) },
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
                    value  = convertMillisToLocalDateString(dateState.selectedDateMillis),
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
                val options = listOf(Option("EMI", "EMI"), Option("Elektra", "Elektra"))
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
                          if (viewModel.isFormValid(name,cover,genero,recordLabel, convertMillisToLocalDateString(dateState.selectedDateMillis))) {
                              val album = AlbumRegistrationDto(name = name, cover = cover, genre = genero, releaseDate = Date(convertMillisToLocalDateString(dateState.selectedDateMillis)), recordLabel = recordLabel, description = descripcion)
                              viewModel.createAlbum(album)
                          }
                        else {
                              showMessage(SNACKBAR_ERROR, ALBUM_CAMPOS_INVALIDOS)
                        }
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