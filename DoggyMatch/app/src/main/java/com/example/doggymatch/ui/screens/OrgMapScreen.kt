package com.example.doggymatch.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.doggymatch.ui.components.OsmMapView

@Composable
fun OrgMapScreen(
    orgName: String,
    latitude: Double,
    longitude: Double,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    if (latitude == 0.0 || longitude == 0.0) {
                        // Display "Map not available" message in the center
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            Text(
                                text = "Map not available",
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }
                    } else {
                        // Show the map
                        OsmMapView(
                            latitude = latitude,
                            longitude = longitude,
                            zoomLevel = 10.0,
                            markerTitle = orgName,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}
