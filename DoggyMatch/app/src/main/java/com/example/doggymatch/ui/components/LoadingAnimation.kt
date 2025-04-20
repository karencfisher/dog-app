package com.example.doggymatch.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.doggymatch.R

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    pawSize: Int = 20,
    pawSpacing: Int = 10
) {
    val infiniteTransition = rememberInfiniteTransition()

    val anchor1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val anchor2 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            initialStartOffset = StartOffset(500, StartOffsetType.Delay),
            repeatMode = RepeatMode.Reverse
        )
    )

    fun Modifier.dotBehavior(anchor: Float) = this
            .offset {
                IntOffset(
                    x = 0,  // No horizontal movement
                    y = lerp(0, 250, anchor)
                )
            }
            .alpha(lerp(0f, 1f, anchor))

    // Wrap with a Box to center content
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        // Row for the paw prints
        Row(
            horizontalArrangement = Arrangement.spacedBy(pawSpacing.dp),
            modifier = Modifier.padding(pawSpacing.dp),
        ) {
            Box(
                modifier = Modifier.dotBehavior(anchor1)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dog_paw),
                    contentDescription = "Loading paw",
                    modifier = Modifier.size(pawSize.dp)
                )
            }
            Box(
                modifier = Modifier.dotBehavior(anchor2)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dog_paw),
                    contentDescription = "Loading paw",
                    modifier = Modifier.size(pawSize.dp)
                )
            }
        }
    }
}