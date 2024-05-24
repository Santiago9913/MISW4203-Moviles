package com.grupo3.vinilos.collector.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel

import com.grupo3.vinilos.R
import com.grupo3.vinilos.ui.theme.Typography
import com.grupo3.vinilos.ui.theme.UiPadding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun CollectorDetail(
    viewModel: CollectorDetailViewModel = viewModel(),
    collectorId: String?
) {
    collectorId?.let {
        val context = LocalContext.current
        val state by viewModel.state.collectAsState()

        LaunchedEffect(state.errorMessage) {
            state.errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

            }
        }
        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                viewModel.getCollector(collectorId.toInt())
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
                        .padding(
                            top = UiPadding.medium,
                            bottom = UiPadding.medium,
                        ),
                    horizontalArrangement = Arrangement.Center,

                    ) {
                    Text(
                        text = stringResource(id = R.string.collector_detail_title),
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }
                OutlinedTextField(
                    value = state.collector.name,
                    onValueChange = {},
                    enabled = true,
                    readOnly = true,
                    label = { Text(text = stringResource(id = R.string.name_label)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = UiPadding.medium)
                )
                OutlinedTextField(
                    value = state.collector.telephone,
                    onValueChange = {},
                    enabled = true,
                    readOnly = true,
                    label = { Text(text = stringResource(id = R.string.phone_label)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = UiPadding.medium)
                )
                OutlinedTextField(
                    value = state.collector.email,
                    onValueChange = {},
                    enabled = true,
                    readOnly = true,
                    label = { Text(text = stringResource(id = R.string.email_label)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = UiPadding.medium)
                )
            }
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.collector_not_available),
                style = Typography.titleMedium
            )
        }
    }
}