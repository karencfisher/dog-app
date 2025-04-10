package com.example.doggymatch.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.doggymatch.models.DogBreedsDescriptions

@Composable
fun DogBreedCard(breed: DogBreedsDescriptions) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = breed.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Don't use let here - just directly use the URL
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(breed.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Image of ${breed.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = breed.description,
                style = MaterialTheme.typography.bodyMedium
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Use a Row with the BreedAttribute components
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BreedAttribute(
                    "Temperament",
                    breed.temperament,
                    Modifier.weight(1f)
                )
                BreedAttribute(
                    "Life Expectancy",
                    "${breed.maxLifeExpectancy} years",
                    Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun BreedAttribute(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(end = 16.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
    }
}
