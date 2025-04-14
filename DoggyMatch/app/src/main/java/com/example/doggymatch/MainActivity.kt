package com.example.doggymatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.doggymatch.ui.screens.DogSearchScreen
import com.example.doggymatch.ui.screens.DogsBreedsDescriptionsScreen
import com.example.doggymatch.ui.screens.DogsBreedsSelectorsScreen
import com.example.doggymatch.ui.screens.HomeScreen
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
                            HomeScreen(goToScreen = { screen ->
                                when (screen) {
                                    "BreedSelection" -> navController.navigate(Destinations.BreedSelection)
                                    "BreedDescription" -> navController.navigate(Destinations.BreedDescriptions)
                                    "DogDetails" -> navController.navigate(Destinations.DogDetails)
                                    else -> {}
                                }
                            })
                        }
                        composable<Destinations.BreedSelection> {
                            DogsBreedsSelectorsScreen(goToDescriptions = {
                                navController.navigate(Destinations.BreedDescriptions)
                            })
                        }
                        composable<Destinations.BreedDescriptions> {
                            DogsBreedsDescriptionsScreen(goBack = {
                                navController.popBackStack()
                            },
                            goToDogSearch = { breedId, breedName ->
                                navController.navigate(Destinations.DogSearch(
                                    breedId,
                                    breedName = breedName)
                                )
                            })
                        }
                        composable<Destinations.DogSearch> {
                            DogSearchScreen(
                                breedId = it.toRoute<Destinations.DogSearch>().breedId ?: 0,
                                breedName = it.toRoute<Destinations.DogSearch>().breedName ?: "",
                                goBack = {
                                    navController.popBackStack()
                                },
                            )
                        }
                        composable<Destinations.DogDetails> {

                        }
                    }
                }
            }
        }
    }
}

