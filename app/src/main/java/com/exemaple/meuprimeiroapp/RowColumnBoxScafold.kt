package com.exemaple.meuprimeiroapp

import android.R.attr.contentDescription
import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SimpleRow(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = {}) {
            Text("Cancel")
        }

        Button(onClick = {}) {
            Text("Confirm")
        }
    }
}

@Composable
fun SimpleColumn(){
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Profile picture", modifier = Modifier.size(100.dp))
        Text("Jane Doe", style = MaterialTheme.typography.headlineLarge)
        Text(
            "Android Development", style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
fun SimpleBox(){
    Box(
        modifier = Modifier.size(120.dp).background(Color.LightGray),
        contentAlignment = Alignment.TopEnd
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Photo",
            modifier = Modifier.fillMaxSize()
        )

        Text(
            text = "New",
            color = Color.White,
            modifier = Modifier.background(Color.Red, shape = CircleShape).padding(4.dp)
        )
    }
}

@Composable
fun ModifiedText(){
    Text(
        text = "Hello, Compose!",
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color.Green)
            .fillMaxWidth()
    )}

@OptIn(ExperimentalMaterial3Api::class) // TopAppBar ainda exige essa anotação no M3
@Composable
fun SimpleScaffold(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My App") },
                // FORMA CORRETA NO MATERIAL 3:
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Cor da barra
                    titleContentColor = MaterialTheme.colorScheme.onPrimary // Cor do texto
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {Text("+") }
        }
    ) { paddingValues -> Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
    ) {
        Text("Welcome to my app!", style = MaterialTheme.typography.headlineMedium)
    }

    }
}

@Composable
fun FormField(){
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun AlignedContent(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Centered Text", modifier = Modifier.align(Alignment.TopStart))
    }
}

@Composable
fun ClickableCard(){
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp).clickable{}.border(1.dp, Color.Gray,
            RoundedCornerShape(8.dp))
    ) {
        Text("Clickable Card", modifier = Modifier.padding(16.dp))
    }
}

@Preview
@Composable
fun SimpleBoxPreview(){
    SimpleBox()
}


@Preview
@Composable
fun SimpleColumnPreview(){
    SimpleColumn()
}


@Preview
@Composable
fun SimpleRowPreview(){
    SimpleRow()
}


@Preview
@Composable
fun ModifiedTextPreview(){
    ModifiedText()
}

@Preview
@Composable
fun SimpleScaffoldPreview(){
    SimpleScaffold()
}

@Preview
@Composable
fun FormFieldPreview(){
    FormField()
}

@Preview
@Composable
fun AlignedContentPreview(){
    AlignedContent()
}

@Preview
@Composable
fun ClickableCardPreview(){
    ClickableCard()
}

