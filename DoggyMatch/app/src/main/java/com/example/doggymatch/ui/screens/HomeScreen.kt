package com.example.doggymatch.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

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
                    .padding(16.dp)
                    .clickable { goToScreen("BreedSelection") },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Select the breed of dog you want to adopt",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { goToScreen("BreedDescription") },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "View your chosen breed",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { goToScreen("DogsDetails") },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "View your possible dog matches",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


