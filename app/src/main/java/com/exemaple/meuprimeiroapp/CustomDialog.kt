package com.exemaple.meuprimeiroapp

import android.view.Surface
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomDialog(
    onDismissRequest: () -> Unit,
    titleContent: @Composable () -> Unit,
    bodyContent: @Composable () -> Unit,
    buttonsContent: @Composable () -> Unit,
    modifier: Modifier = Modifier
){
    Dialog(
        onDismissRequest = onDismissRequest
    ){
        Surface(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface

        ){
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                titleContent()
                bodyContent()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    buttonsContent()
                }
            }

        }
    }
}

@Composable
fun MyScreen(){
    var showDialog by remember { mutableStateOf(false) }
    Button({showDialog = true}) {
        Text("Show Dialog")
    }
    if(showDialog){
        CustomDialog(
            onDismissRequest = {showDialog = false},
            titleContent = {Text("Confirm Action", style = MaterialTheme.typography.headlineSmall)},
            bodyContent = {Text("Are you sure you want to proceed?")},
            buttonsContent = {
                TextButton({showDialog = false}) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button({showDialog = false}) {
                    Text("OK")
                }
            }
        )

    }
}

@Preview
@Composable
fun MyScreenPreview(){
    MyAppTheme {
        CustomDialog(
            onDismissRequest = {},
            titleContent = { Text("Title") },
            bodyContent = { Text("Body") },
            buttonsContent = {
                TextButton({ }) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button({ }) {
                    Text("OK")
                }
            }
        )
    }
}