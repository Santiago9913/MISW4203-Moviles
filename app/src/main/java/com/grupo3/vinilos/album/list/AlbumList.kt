package com.grupo3.vinilos.album.list

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.grupo3.vinilos.R
import com.grupo3.vinilos.ui.theme.Typography
import com.grupo3.vinilos.ui.theme.UiPadding
import com.grupo3.vinilos.utils.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

@Composable
fun AlbumList(
    viewModel: AlbumsViewModel = viewModel(),
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
        withContext(Dispatchers.IO) {
            viewModel.getAlbums()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.albums.isEmpty()) {
            Text(
                text = stringResource(id = R.string.albums_not_available),
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
                items(state.albums) { album ->
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .clickable {
                                var route = StringBuilder()
                                    .append(Screen.AlbumDetail.route)
                                    .toString()
                                    .replace("{albumId}", album.id.toString())
                                navigateTo(route)
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