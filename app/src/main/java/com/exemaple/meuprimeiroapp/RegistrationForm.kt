package com.exemaple.meuprimeiroapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RegistrationForm() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }


    fun validateForm(): Boolean {
        val isNameValid = name.trim().length >= 3
        val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.trim().length >= 6
        nameError = if (isNameValid) "" else "Name must have at least 3 characters"
        emailError = if (isEmailValid) "" else "Invalid email address"
        passwordError = if (isPasswordValid) "" else "Password must have at least 6 characters"
        return isNameValid && isEmailValid && isPasswordValid
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            "Register",
            style = MaterialTheme.typography.headlineLarge,
        )

        TextField(
            value = name,
            onValueChange = { name = it; if (nameError.isNotEmpty()) validateForm() },
            label = { Text("Name") },
            isError = nameError.isNotEmpty(),
            supportingText = { if (nameError.isNotEmpty()) Text(nameError) },
            modifier = Modifier.fillMaxWidth()

        )

        TextField(
            value = email,
            onValueChange = { email = it; if (emailError.isNotEmpty()) validateForm() },
            label = { Text("Email") },
            isError = emailError.isNotEmpty(),
            supportingText = { if (emailError.isNotEmpty()) Text(emailError) },
            modifier = Modifier.fillMaxWidth()

        )

        TextField(
            value = password,
            onValueChange = { password = it; if (passwordError.isNotEmpty()) validateForm() },
            label = { Text("Password") },
            isError = passwordError.isNotEmpty(),
            supportingText = { if (passwordError.isNotEmpty()) Text(passwordError) },
            modifier = Modifier.fillMaxWidth()

        )

        Button(

            {
                if (validateForm()) {
                    //TODO: Register user
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
        ) {
            Text("Register")
        }


    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationFormPreview() {
    RegistrationForm()
}