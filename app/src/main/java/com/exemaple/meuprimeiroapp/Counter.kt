package com.exemaple.meuprimeiroapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.exemaple.meuprimeiroapp.Counter

@Composable
private fun Counter(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text("$count", style = MaterialTheme.typography.titleMedium)
        Row(
           horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onDecrement) {
                Text("Decrement")
            }
            Button(onIncrement) {
                Text("Increment")
            }
        }
    }
}

@Composable
fun CounterScreen() {
    var count by remember {mutableStateOf(0)}
    Counter(
        count = count,
        onIncrement = {count++ },
        onDecrement = {count--; if(count < 0) count = 0}
    )
}


@Preview(showBackground = true)
@Composable
fun CounterPreview(){
    Counter(count = 0, onIncrement = {}, onDecrement = {})
}


