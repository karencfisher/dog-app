package com.example.doggymatch.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.doggymatch.models.Animal

@Composable
fun DogCard(
    animal: Animal
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        var isVisible by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .clickable { isVisible = !isVisible },
            ) {
                Text(
                    text = animal.attributes.name ?: "Unknown Animal",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = animal.attributes.breedString ?: "Unknown",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            AnimatedVisibility(visible = isVisible) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    animal.attributes.largerThumbnailUrl?.takeIf { it.isNotEmpty() }
                        ?.let { imageUrl ->
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = "Photo of ${animal.attributes.name ?: "pet"}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentScale = ContentScale.Inside
                            )
                        }
                    Text(text = "Age: ${animal.attributes.ageString ?: "Unknown"}")
                    Text(text = "Size: ${animal.attributes.sizeGroup ?: "Unknown"}")
                    Text(text = "Description: ${animal.attributes.cleanDescription}")
                    Text(text = "Distance: ${animal.attributes.distance ?: "Unknown"} miles")

                    // You can access relationships like:
                    animal.relationships.orgs?.data?.firstOrNull()?.id?.let {
                        Text(text = "Organization ID: $it")
                    }
                }
            }
        }
    }
}