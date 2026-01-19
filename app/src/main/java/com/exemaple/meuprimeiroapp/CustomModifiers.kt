package com.exemaple.meuprimeiroapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// O código do livro foi modificado para refletir corretamente o comportamento esperado para essa atividade
fun Modifier.gradientBackground(colors: List<Color>, direction: GradientDirection = GradientDirection.StartToEnd) = this.then(
    Modifier.background(
        brush = when (direction) {
            // Se não passarmos start e end, o Compose usa o tamanho total do componente automaticamente
            GradientDirection.StartToEnd -> Brush.linearGradient(colors)
            GradientDirection.TopToBottom -> Brush.verticalGradient(colors)
        }
    )
)



@Composable
fun GradientCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .gradientBackground(
                    colors = listOf(Color.Blue, Color.Cyan),
                    direction = GradientDirection.TopToBottom
                )
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Gradient Card", color = Color.White, style = MaterialTheme.typography.headlineSmall)
            Text("Agora com transição real", color = Color.White.copy(alpha = 0.8f))
        }
    }
}

enum class GradientDirection{
    StartToEnd,
    TopToBottom
}


@Preview
@Composable
fun GradientCardPreview(){
    GradientCard()
}