package com.grupo3.vinilos.collector.list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grupo3.vinilos.R
import com.grupo3.vinilos.ui.theme.Typography
import com.grupo3.vinilos.ui.theme.UiPadding
import com.grupo3.vinilos.utils.Screen


@Composable
fun CollectorList(
    viewModel: CollectorsViewModel = viewModel(),
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
        viewModel.getCollectors()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (state.collectors.isEmpty()) {
            Text(
                text = stringResource(id = R.string.collectors_not_available),
                style = Typography.titleMedium,
                textAlign = TextAlign.Center,
            )
        } else {
            LazyColumn(
                Modifier
                    .padding(
                        top = UiPadding.medium, start = UiPadding.large, end = UiPadding.large
                    )
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                items(state.collectors) { collector ->
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .clickable {
                                var route = StringBuilder()
                                    .append(Screen.CollectorDetail.route)
                                    .toString()
                                    .replace("{collectorId}", collector.id.toString())
                                navigateTo(route)
                            }) {
                        Column {
                            Text(
                                text = collector.name, style = Typography.titleMedium
                            )
                            Text(
                                text = collector.email, style = Typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}