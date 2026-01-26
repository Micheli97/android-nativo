package com.exemaple.meuprimeiroapp.gerenciamento.estados.complexos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // IMPORTANTE: para o items(cart)
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State // IMPORTANTE: tipo State
import androidx.compose.runtime.getValue // IMPORTANTE: para o 'by'
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// Modelo de dados
data class CartItem(val id: String, val name: String, val price: Double, val quantity: Int)

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {
    // Estado privado (mutável)
    private val _cart = mutableStateOf<List<CartItem>>(emptyList())

    // Estado público (somente leitura)
    // O getter conecta a propriedade pública ao valor privado
    val cart: State<List<CartItem>> get() = _cart

    fun addItem(item: CartItem) {
        // No Compose, para a UI atualizar, precisamos de uma nova instância da lista
        _cart.value += item
    }

    fun updateQuantity(id: String, quantity: Int) {
        _cart.value = _cart.value.map {
            if (it.id == id) it.copy(quantity = quantity) else it
        }
    }
}



@Composable
fun ShoppingCartScreen(viewModel: CartViewModel = hiltViewModel()) {
    // O 'by' permite usar 'cart' diretamente como List em vez de cart.value
    val cart by viewModel.cart

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Shopping Cart",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // LazyColumn é o equivalente ao ListView.builder do Flutter
        LazyColumn(
            modifier = Modifier.weight(1f) // Ocupa o espaço disponível, empurrando o botão para baixo
        ) {
            items(cart) { item ->
                CartItemRow(
                    item = item,
                    onIncrement = { viewModel.updateQuantity(item.id, item.quantity + 1) }
                )
            }
        }

        Button(
            onClick = {
                val id = System.currentTimeMillis().toString()
                viewModel.addItem(CartItem(id, "Item ${cart.size + 1}", 10.0, 1))
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Add Item")
        }
    }
}

@Composable
fun CartItemRow(item: CartItem, onIncrement: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(item.name, style = MaterialTheme.typography.bodyLarge)
            Text("$${item.price}", style = MaterialTheme.typography.bodySmall)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Qty: ${item.quantity}", modifier = Modifier.padding(end = 8.dp))
            Button(onClick = onIncrement) {
                Text("+")
            }
        }
    }
}