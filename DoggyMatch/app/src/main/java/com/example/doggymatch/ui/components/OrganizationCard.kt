package com.example.doggymatch.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.doggymatch.models.Organizations

@Composable
fun OrganizationCard(
    organization: Organizations,
    goToMap: (Double, Double) -> Unit = { _, _ -> },
) {
    var isVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
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
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())

                ) {
                    Text(
                        text = organization.name ?: "Unknown Organization",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 8.dp)
                            .clickable { isVisible = !isVisible },
                    ) {
                        Text(
                            "About (click to ${if (isVisible) "hide" else "show"})",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(bottom = 8.dp),
                        )
                    }
                    AnimatedVisibility(
                        visible = isVisible,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        LinkifyTextView(
                            text = organization.about ?: "No description available",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(bottom = 8.dp),
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = "Adoption Process",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(bottom = 8.dp)

                    )
                    LinkifyTextView(
                        text = organization.adoptionProcess ?: "No adoption process available",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Text(
                        text = "Contact Information",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Phone: ${organization.phone ?: "No phone number available"}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "Email: ${organization.email ?: "No email available"}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    LinkifyTextView(
                        text = "Website: ${organization.website ?: "No website available"}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    LinkifyTextView(
                        text = "Facebook: ${organization.facebook ?: "No Facebook available"}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Text(
                        text = "Location",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = buildString {
                            if (!organization.address.isNullOrEmpty()) {
                                append(organization.address)
                                append("\n")
                            }
                            if (!organization.city.isNullOrEmpty()) {
                                append(organization.city)
                                if (!organization.state.isNullOrEmpty()) {
                                    append(", ")
                                    append(organization.state)
                                }
                                if (!organization.zip.isNullOrEmpty()) {
                                    append(" ")
                                    append(organization.zip)
                                }
                            } else {
                                if (!organization.state.isNullOrEmpty()) {
                                    append(organization.state)
                                    if (!organization.zip.isNullOrEmpty()) {
                                        append(" ")
                                        append(organization.zip)
                                    }
                                } else if (!organization.zip.isNullOrEmpty()) {
                                    append(organization.zip)
                                }
                            }
                            if (isEmpty()) append("No address available")
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Button(
                        onClick = {
                            goToMap(
                                organization.latitude?.toDouble() ?: 0.0,
                                organization.longitude?.toDouble() ?: 0.0
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                    ) {
                        Text(text = "View on Map")
                    }
                }
            }
        }
    }
}