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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.grupo3.vinilos.artists.dto.ArtistDto
import com.grupo3.vinilos.ui.theme.Typography
import java.time.LocalDate

@Composable
fun ArtistList() {
    val artists = listOf(
        ArtistDto(
            id = 1,
            name = "Santiago",
            image = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Ruben_Blades_by_Gage_Skidmore.jpg/800px-Ruben_Blades_by_Gage_Skidmore.jpg",
            description = "Es un cantante, compositor, músico, actor, abogado, político y activista panameño. Ha desarrollado gran parte de su carrera artística en la ciudad de Nueva York.",
            birthDate = LocalDate.parse("1948-07-16"),
            creationDate = LocalDate.parse("1948-07-16"),
            type = "Musician",
            bandId = "1"
        ),
    )

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
        items(artists) { artist ->
            Box(
                Modifier
                    .clickable {
                        println(artist.id)
                    }
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
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