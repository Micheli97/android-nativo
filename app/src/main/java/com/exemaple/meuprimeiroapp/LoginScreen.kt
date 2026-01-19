package com.exemaple.meuprimeiroapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        val isWideScreen = this@BoxWithConstraints.maxWidth > 600.dp
        Column(modifier = Modifier.fillMaxWidth().
        padding(16.dp).
        align(Alignment.Center ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)){
            Text("Login",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp))
            TextField(
                value = email,
                onValueChange = {email = it},
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(if(isWideScreen) 0.5f else 1f)
                    .semantics{contentDescription = "Email input"}
            )
            TextField(
                value = password,
                onValueChange = {password = it},
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(if(isWideScreen) 0.5f else 1f)
                    .semantics{ contentDescription = "Password input"}
            )
            Button(onClick = {},
                modifier = Modifier.fillMaxWidth(if(isWideScreen) 0.5f else 1f).height(48.dp))
                {Text("Login") }
            Text("Forgot password?",
                color = MaterialTheme.colorScheme.primary,
                modifier =   Modifier.clickable { /*TODO*/ }
                    .padding(8.dp))

        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = "spec:width=1280dp,height=800dp,dpi=240")
@Composable
fun LoginScreenPreview(){
   MaterialTheme {
       LoginScreen()
   }
}