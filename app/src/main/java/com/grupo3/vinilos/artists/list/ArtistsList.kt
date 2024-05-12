package com.grupo3.vinilos.artists.list


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.grupo3.vinilos.ui.theme.Typography
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grupo3.vinilos.R
import com.grupo3.vinilos.ui.theme.UiPadding
import com.grupo3.vinilos.utils.Screen


@Composable
fun ArtistList(
    viewModel: ArtistsViewModel = viewModel(),
    navigateTo: (String) -> Unit
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        }
    }
    LaunchedEffect(Unit) {
        viewModel.getArtists()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.artists.isEmpty()) {
            Text(
                text = stringResource(id = R.string.artists_not_available),
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
                items(state.artists) { artist ->
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .clickable {
                                // TODO: implement the action to go to the artist detail screen
                                var route = StringBuilder()
                                    .append(Screen.ArtistDetail.route)
                                    .toString()
                                    .replace("{artistId}", artist.id.toString())
                                navigateTo(route)
                            }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = UiPadding.medium)
                        ) {
                            AsyncImage(
                                model = artist.image,
                                contentDescription = artist.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(UiPadding.xxLarge)
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