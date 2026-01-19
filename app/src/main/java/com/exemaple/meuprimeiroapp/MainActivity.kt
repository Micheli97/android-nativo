package com.exemaple.meuprimeiroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.exemaple.meuprimeiroapp.ui.theme.MeuPrimeiroAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme{
               PhotoGallery(
                   listOf(
                       R.drawable.ic_launcher_background to "Sunset",
                       R.drawable.ic_launcher_foreground to "Mountain"
                   )
               )

            }
        }
    }
}


@Composable
fun HelloWorldScreen(name: String){
    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,) {
        Text(text = "Hello $name!", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {/* DO nothing for now */ }) {
            Text(text = "Click me")
        }

    }
}


// Composable se integram perfeitamente com os recursos do Kotlin, permitindo o uso de condicionais
// para mostrar ou esconder elementos da interface, loops para renderizar lista ou funções de extensão
// para adicionar comportamentos personalizados.
@Composable
fun UserList(user: List<String>){
    Column() {
        user.forEach { user ->
            Text(text = user)
        }
    }
}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    // [mutableStateOf] -. cria uma variável com estado (objeto observável)
    // [remember] -> armazena o estado da variável em memória, para manter o valor entre recomposições
    // Recomponsição inteligante
    var textoOriginal by remember { mutableStateOf("Bem vindo ao meu app, $name!") }

    /*
    Ao contrário do Flutter que ao chamar o setState(). reconstroi o StatefullWidget, no Compose
    apenas a função que teve seu estado alterado é recomposta.

    O Compose comparar a nova árvore de UI com a anterior e atualiza paenas os nós afetados. Isso é
    semelhante a como o React e Flutter trabalham com atualizações de interface, mas o Compose otimiza
    para a arquitetura  do Android. Por exemplo, se a Collumn tem multiplos compose de texto, e apenas
    um depende do estado do nome, apenas esse texto será recomposto. Essa otimização é muito importante
    para o desempenho do aplicativo, principalmente em interfaces complexas com centenas de elementos.

    * */


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
        text = textoOriginal,
        color = MaterialTheme.colorScheme.tertiary,
        textAlign = TextAlign.Center,

        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { textoOriginal = "O botão foi clicado com sucesso!!<3"}) {
            Text("Clique aqui")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MeuPrimeiroAppTheme {
        Greeting("Android")
    }
}