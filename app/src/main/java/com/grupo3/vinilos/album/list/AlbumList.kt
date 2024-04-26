package com.grupo3.vinilos.album.list


import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grupo3.vinilos.ui.theme.Typography
import com.grupo3.vinilos.ui.theme.UiPadding


@Composable
fun AlbumList(
    viewModel: AlbumsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAlbums()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.albums.isEmpty()) {
            Text(text = "ALBUMS NO DISPONIBLES EN ESTE MOMENTO", style = Typography.titleMedium)
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
                items(state.albums) { album ->
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .clickable {
                                println(album.id)
                            }
                    ) {
                        Column {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = UiPadding.medium)
                            ) {
                                Column {
                                    Text(
                                        text = album.name, style = Typography.titleMedium,
                                    )
                                    Text(
                                        text = album.description,
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
}