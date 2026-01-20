package com.exemaple.meuprimeiroapp

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SimpleIcon(){
    Icon(
        imageVector = Icons.Filled.Favorite,
        contentDescription = "Favorite",
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier.size(24.dp)
    )
}

@Composable
fun CustomVector(){
    Icon(
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "Custom Vector",
        tint = MaterialTheme.colorScheme.secondary,
        modifier = Modifier.size(32.dp))
}

// Também é possível adicionar imagens em formato PNG, por exemplo
@Composable
fun ResterImage(){
    Image(
        painter = painterResource(  R.drawable.ic_launcher_foreground),
        contentDescription = "APP Logo",
        modifier = Modifier.size(48.dp)
    )
}

@Composable
fun IconButton(){
    Button({}){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
            )
            Text("Send")
        }
    }
}

@Composable
fun ToggleIcon(){
    var isFavorite by remember { mutableStateOf(false) }
Icon(
    imageVector = if(isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
    contentDescription = "Favorite toggle",
    modifier = Modifier
        .size(24.dp)
        .clickable{isFavorite = !isFavorite}
)
}


@Composable
fun RotatingIcon(){
    val rotation = remember { Animatable(0f)  }
    LaunchedEffect(Unit) {
        rotation.animateTo(360f, animationSpec = infiniteRepeatable(tween(1000))
    )

    }
    Icon(
        imageVector = Icons.Filled.Refresh,
        contentDescription = "Refresh",
        modifier = Modifier.size(24.dp)
            .graphicsLayer(rotationZ = rotation.value)
    )
}



