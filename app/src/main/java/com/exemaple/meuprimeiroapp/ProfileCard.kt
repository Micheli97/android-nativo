package com.exemaple.meuprimeiroapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileCard(name: String, bio : String,  onAction: () -> Unit){
    Card(modifier = Modifier.fillMaxSize().padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        var isFollowed by remember { mutableStateOf(false) }

        Row(modifier = Modifier.fillMaxSize().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_background ),
                contentDescription = "User Avatar",
                modifier = Modifier.size(64.dp).clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column() {
                Text(name, style = MaterialTheme.typography.headlineMedium)
                Text(bio, style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    isFollowed = !isFollowed
                    onAction()
                },
                    // Usamos o Modifier para descrever o que o botão faz para o TalkBack
                    modifier = Modifier.semantics{
                        contentDescription = if(isFollowed) "Unfollow" else "Follow"
                    }
                    )
                { Text( if(isFollowed) "Unfollow" else "Follow")}

            }
        }
    }
}

@Preview
@Composable
fun ProfileCardPreview(){
    ProfileCard(name = "John Doe", bio = "Android Developer", onAction = {})
}