package com.exemaple.meuprimeiroapp

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomProgressIndicator(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val rotation = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        rotation.animateTo(
            targetValue = 360f,
            animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing))
        )
    }
    Box(
        modifier = Modifier
            .size(48.dp)
            .graphicsLayer(rotationZ = rotation.value)
    ) {
        content()
    }

}

@Preview
@Composable
fun CustomProgressIndicator() {
    MyAppTheme {
        CustomProgressIndicator {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = "Loading",
                tint = MaterialTheme.colorScheme.primary
            )
        }

    }
}