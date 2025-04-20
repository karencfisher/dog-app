package com.example.doggymatch.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.doggymatch.models.SelectedDogs
import com.example.doggymatch.viewmodels.DogSearchViewModel

@Composable
fun DogCard(
    dog: SelectedDogs,
    viewModel: DogSearchViewModel,
    saveFavorite: (SelectedDogs) -> Unit,
    removeFavorite: (SelectedDogs) -> Unit,
    goToOrganizationDetails: (Int) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        var isVisible by remember { mutableStateOf(false) }
        var favoriteButtonText by remember { mutableStateOf("Add to favorites") }
        val isDogSelected = viewModel.isDogSelected.collectAsState(false)

        LaunchedEffect(dog.dogId) {
            dog.dogId?.let { viewModel.checkIfDogIsSelected(it) }?.join()
            favoriteButtonText = if (isDogSelected.value) {
                "Remove from favorites"
            } else {
                "Add to favorites"
            }
        }

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
                    text = dog.name ?: "Unknown Animal",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = dog.breed ?: "Unknown breed",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            AnimatedVisibility(visible = isVisible) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    dog.imageUrl?.takeIf { it.isNotEmpty() }
                        ?.let { imageUrl ->
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = "Photo of ${dog.name ?: "pet"}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentScale = ContentScale.Inside
                            )
                        }
                    Text(
                        text = dog.description ?: "No description available",
                        color = MaterialTheme.colorScheme.secondary
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Age: ${dog.age ?: "Unknown"}",
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Sex: ${dog.sex ?: "Unknown"}",
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Size: ${dog.size ?: "Unknown"}",
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Distance: ${dog.distance ?: "Unknown"} miles",
                        color = MaterialTheme.colorScheme.secondary
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Button(
                        onClick = {
                            if (favoriteButtonText == "Add to favorites") {
                                saveFavorite(dog)
                                favoriteButtonText = "Remove from favorites"
                            } else {
                                removeFavorite(dog)
                                favoriteButtonText = "Add to favorites"
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(text = favoriteButtonText)
                    }
                    Button(
                        onClick = { goToOrganizationDetails(dog.orgId ?: 0) },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(text = "How can I adopt this cutie?")
                    }
                }
            }
        }
    }
}