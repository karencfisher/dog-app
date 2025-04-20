package com.example.doggymatch.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.doggymatch.R
import com.example.doggymatch.ui.components.LoadingAnimation
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(2500) // Just wait for the splash duration
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp, 140.dp, 0.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.adopt_me_please),
                contentDescription = "Dog image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(top=16.dp),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Doggy Match Loading",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.tertiary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
            LoadingAnimation(
                pawSize = 50,
                pawSpacing = 50
            )
        }
    }
}