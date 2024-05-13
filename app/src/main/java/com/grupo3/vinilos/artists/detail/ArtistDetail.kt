package com.grupo3.vinilos.artists.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.grupo3.vinilos.R
import com.grupo3.vinilos.ui.theme.Accent
import com.grupo3.vinilos.ui.theme.Typography
import com.grupo3.vinilos.ui.theme.UiPadding
import com.grupo3.vinilos.utils.parseDateToDDMMYYYY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun ArtistDetail(
    viewModel: ArtistDetailViewModel = ArtistDetailViewModel(),
    artistId: String?
) {
    artistId?.let {
        val context = LocalContext.current
        val state by viewModel.state.collectAsState()

        LaunchedEffect(state.errorMessage) {
            state.errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

            }
        }
        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                viewModel.getArtist(artistId.toInt())
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
                        .fillMaxWidth()
                        .height(360.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    AsyncImage(
                        model = state.artist.image,
                        modifier = Modifier
                            .height(300.dp)
                            .width(300.dp)
                            .padding(
                                top = UiPadding.medium,
                                bottom = UiPadding.medium,
                            )
                            .testTag("artistImage_" + state.artist.image),
                        contentDescription = stringResource(id = R.string.artists_not_available)
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
                        text = state.artist.name,
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
                        "${stringResource(id = R.string.artist_fecha_nacimiento_label)}: ${
                            state.artist.birthDate?.let { it1 ->
                                parseDateToDDMMYYYY(it1)
                            } ?: run {
                                state.artist.creationDate?.let { it1 -> parseDateToDDMMYYYY(it1) }
                            }
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
                        state.artist.description,
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
                    Button(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Accent,
                        ),
                    ) {
                        Text(text = "Albumes")
                    }
                }
                if (state.artist.creationDate != null) {
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

                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Accent,
                            ),
                        ) {
                            Text(text = "Musicos")
                        }
                    }
                }
            }
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.artists_not_available),
                style = Typography.titleMedium
            )
        }
    }
}