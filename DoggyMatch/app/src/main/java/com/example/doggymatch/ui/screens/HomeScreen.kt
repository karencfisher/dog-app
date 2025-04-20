package com.example.doggymatch.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.doggymatch.R

@Composable
fun HomeScreen(
    goToScreen: (String) -> Unit,
) {
    // Use a Box to center the content
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()  // Add this to make Box take full width
                    .padding(16.dp)
                    .clickable { goToScreen("BreedSelection") },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Select breeds of dog you may want to adopt",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()  // Add this to make Box take full width
                    .padding(16.dp)
                    .clickable { goToScreen("BreedDescription") },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "View your selected breeds",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()  // Add this to make Box take full width
                    .padding(16.dp)
                    .clickable { goToScreen("DogDetails") },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "View your favorite dogs",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
            }
            Image(
                painter = painterResource(id = R.drawable.adopt_me_please),
                contentDescription = "Dog image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=16.dp),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}
