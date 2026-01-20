package com.exemaple.meuprimeiroapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


/*
* @OptIn(ExperimentalMaterial3Api::class) não significa que o código está depreciado (obsoleto),
* mas sim o contrário: ele é novo demais.
*
* Depreciado (Deprecated): É um código antigo que será removido no futuro. O compilador sugere que
* você pare de usar e mude para algo mais novo.

* Experimental: É uma API nova que já está disponível para uso, mas a equipe do Google ainda não "bateu o martelo"
* sobre a sua forma final. Eles reservam o direito de mudar o nome de um parâmetro ou a estrutura da função em
* versões futuras sem que isso seja considerado uma quebra de contrato com o desenvolvedor.
* */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer(){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent()
        }
    ){
        Scaffold(
            topBar ={
                TopAppBar(
                    title = { Text("App Name") },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            modifier = Modifier.clickable{
                                /*
                                * No Flutter, abrir o drawer é uma função síncrona que o Framework gerencia internamente.
                                * No Compose, abrir o drawer é uma animação que leva tempo. O Kotlin exige que funções
                                * que "esperam" o tempo passar sejam executadas dentro de uma Corrotina para não travar a interface do usuário.
                                * */
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    }
                )
            }
        ) {
            paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) {Text("Main Content")
            }
        }
    }

}

@Composable
fun DrawerContent(){
    Column(modifier = Modifier.fillMaxHeight()
        .width(280.dp)
        .background(MaterialTheme.colorScheme.surface)
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp))
        {
        Text(
            "Menu",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
            DrawerItem(Icons.Filled.Home, text= "Home")
            DrawerItem(Icons.Filled.Person, text= "Profile")
            DrawerItem(Icons.Filled.Settings, text= "Settings")
        }
}

@Composable
fun DrawerItem(icon: ImageVector, text: String){
    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable{}
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = text, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }

}

