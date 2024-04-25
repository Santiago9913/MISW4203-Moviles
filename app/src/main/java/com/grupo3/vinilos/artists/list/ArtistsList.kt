package com.grupo3.vinilos.artists.list


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.grupo3.vinilos.ui.theme.Typography
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ArtistList(
    viewModel: ArtistsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getArtists();
    }

    LazyColumn(
        Modifier
            .padding(
                top = 8.dp,
                start = 16.dp,
                end = 16.dp
            )
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(state.artists) { artist ->
            Box(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clickable {
                        println(artist.id)
                    }
            ) {
                Column {
                    if (state.artists.isEmpty()) {
                        Text(text = "Cargando...")
                    } else {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
                            AsyncImage(
                                model = artist.image,
                                contentDescription = artist.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(
                                        CircleShape
                                    )
                            )

                            Column {
                                Text(
                                    text = artist.name, style = Typography.titleMedium,
                                )
                                Text(
                                    text = artist.description,
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
}