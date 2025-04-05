package com.example.doggymatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doggymatch.ui.screens.DogsBreedsSelectorsScreen
import com.example.doggymatch.ui.theme.DoggyMatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            DoggyMatchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "dogsBreedsSelectors",
                        Modifier.padding(innerPadding)
                    ) {
                        composable("dogsBreedsSelectors") {
                            DogsBreedsSelectorsScreen()
                        }
                    }
                }
            }
        }
    }
}

