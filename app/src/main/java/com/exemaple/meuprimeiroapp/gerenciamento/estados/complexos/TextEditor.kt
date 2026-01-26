package com.exemaple.meuprimeiroapp.gerenciamento.estados.complexos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State // IMPORTANTE: Import do State correto
import androidx.compose.runtime.getValue // IMPORTANTE: Para usar o 'by'
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel // Import recomendado para Compose Navigation
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor() : ViewModel() {
    private val _text = mutableStateOf("")

    // Garantindo que o tipo retornado seja o State do Compose
    val text: State<String> get() = _text

    // Lista simples para o histórico de Undo/Redo
    private val history = mutableListOf("")
    private var historyIndex = 0

    fun updateText(newText: String) {
        if (newText != _text.value) {
            // Remove o futuro se houver mudanças após um Undo
            while (historyIndex < history.size - 1) {
                history.removeAt(history.size - 1)
            }

            history.add(newText)
            historyIndex++
            _text.value = newText
        }
    }

    fun undo() {
        if (historyIndex > 0) {
            historyIndex--
            _text.value = history[historyIndex]
        }
    }

    fun redo() {
        if (historyIndex < history.size - 1) {
            historyIndex++
            _text.value = history[historyIndex]
        }
    }
}



@Composable
fun TextEditorScreen(viewModel: EditorViewModel = hiltViewModel()) {
    // Agora o 'by' funciona devido ao import do getValue
    val text by viewModel.text

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { viewModel.updateText(it) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Digite algo") }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { viewModel.undo() },
                enabled = true // Você pode adicionar lógica de habilitar/desabilitar depois
            ) {
                Text("Undo")
            }
            Button(
                onClick = { viewModel.redo() }
            ) {
                Text("Redo")
            }
        }
    }
}