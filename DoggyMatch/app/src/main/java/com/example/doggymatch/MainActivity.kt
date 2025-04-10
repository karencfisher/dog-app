package com.example.doggymatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doggymatch.ui.screens.DogsBreedsDescriptionsScreen
import com.example.doggymatch.ui.screens.DogsBreedsSelectorsScreen
import com.example.doggymatch.ui.theme.DoggyMatchTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            DoggyMatchTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(
                                    "Doggy Match",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            modifier = Modifier.height(56.dp),
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.primary,
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                IconButton(onClick = { navController.navigate(Destinations.Home) }) {
                                    Icon(imageVector = Icons.Default.Home,
                                        contentDescription = "Home",
                                        modifier = Modifier.size(30.dp))
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Destinations.Home,
                        Modifier.padding(innerPadding)
                    ) {
                        composable<Destinations.Home> {
                            Column (
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            )  {
                                Box(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .clickable { navController.navigate(Destinations.BreedSelection) },
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
                                        .clickable { navController.navigate(Destinations.BreedDescriptions) },
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
                                        .clickable { navController.navigate(Destinations.BreedSelection) },
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
                        composable<Destinations.BreedSelection> {
                            DogsBreedsSelectorsScreen(goToDescriptions = {
                                navController.navigate(Destinations.BreedDescriptions)
                            })
                        }
                        composable<Destinations.BreedDescriptions> {
                            DogsBreedsDescriptionsScreen(goBack = {
                                navController.popBackStack()
                            })
                        }
                        composable<Destinations.DogSearch> {

                        }
                        composable<Destinations.DogDetails> {

                        }
                        composable<Destinations.DogAdoptionDetails> {

                        }
                    }
                }
            }
        }
    }
}

