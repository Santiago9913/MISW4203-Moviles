package com.grupo3.vinilos.album.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.grupo3.vinilos.R
import com.grupo3.vinilos.ui.theme.Accent
import com.grupo3.vinilos.ui.theme.UiPadding
import com.grupo3.vinilos.utils.Screen
import com.grupo3.vinilos.utils.navigateToWithState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetail(
    navigateTo: (String) -> Unit,
    albumId: String?

) {
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

                /* TODO: ESTA IMAGEN SERA REEMPLAZADA POR LA IMAGEN DE LA API */
                Image(
                    modifier = Modifier
                        .height(300.dp)
                        .width(300.dp)
                        .padding(
                            top = UiPadding.medium,
                            bottom = UiPadding.medium,
                        ),
                    painter = painterResource(id = R.drawable.album),
                    contentDescription = stringResource(id = R.string.albums_not_available)
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
                    text = "Titulo",
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
                    "Fecha de lanzamiento: 01/01/2000",
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
                    "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. ",
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
                SuggestionChip(
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        labelColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.secondary,
                        disabledContainerColor = MaterialTheme.colorScheme.secondary,
                        disabledLabelColor = Color.White
                    ),
                    modifier = Modifier.height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {},
                    label = { Text("Salsa") },
                    enabled = false,
                    border = SuggestionChipDefaults.suggestionChipBorder(borderWidth = 0.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                SuggestionChip(
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        labelColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.secondary,
                        disabledContainerColor = MaterialTheme.colorScheme.secondary,
                        disabledLabelColor = Color.White
                    ),
                    modifier = Modifier.height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {},
                    enabled = false,
                    label = { Text("EMI Music") },
                    border = SuggestionChipDefaults.suggestionChipBorder(borderWidth = 0.dp)
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
                    Text(text = "Canciones")
                }
            }
        }
    }
}