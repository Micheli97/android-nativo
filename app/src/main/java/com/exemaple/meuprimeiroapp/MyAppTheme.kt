package com.exemaple.meuprimeiroapp

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.unit.dp


/*
* Essa é uma estrutura Singleton do Kotlin. No compose utilziamos o [object] para criar um repositório
* centralizado de constantes que podem ser acessadas de qualquer lugar do código sem a necessidade de
* instanciar uma classe.
*
* Diferente de uma [class], o [object] define uma instância única (Singleton) que é criada na primeira
* vez que é acessada.
*
* [val Primary]: Define uma propriedade imutável dentro desse objeto.
* [Acesso Direto]: Você o chama como AppColors.Primary, sem precisar fazer val colors = AppColors()
*
* */
object AppColors{
    val Primary = Color(0xFF6200EE)
    val Secondary = Color(0xFF03DAC6)
}
@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    val myColors = lightColorScheme(
        primary = AppColors.Primary,
        secondary = AppColors.Secondary,
        background = Color.White,
        surface = Color.White,
        onPrimary = Color.White,
        onBackground = Color.Black,
        onSurface = Color.Black
    )

    // No M3, usamos nomes como displayLarge, bodyLarge, etc.
    // Aqui foi feita uma modificação em relação ao exemplo do livro, já que estou utilizando o m3
    // e o livro o material 2. Houve mudanças bruscas em relação a instanciação e nomeclatura dos estilos
    val myTypography = Typography(
        headlineLarge = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        ),
        bodyLarge = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.SansSerif
        )
    )

    val myShapes = Shapes(
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(12.dp),
        large = RoundedCornerShape(16.dp)
    )

    MaterialTheme(
        colorScheme = myColors,
        typography = myTypography,
        shapes = myShapes,
        content = content
    )
}



// o MaterialTheme também suporta composição. É possível compor diferentes temas para criar estilos
// localizados. No exemplo abaixo o Dialog utliza um color scheme diferente do aplicativo.
@Composable
fun DialogContent(){
    MaterialTheme(colorScheme = darkColorScheme(primary = Color.DarkGray)){
        Text("This dialog has a custom theme")
    }
}