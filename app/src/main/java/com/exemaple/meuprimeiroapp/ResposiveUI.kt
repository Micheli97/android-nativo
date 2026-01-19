package com.exemaple.meuprimeiroapp

import android.content.pm.ConfigurationInfo
import android.content.res.Configuration
import android.widget.Button
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResponsiveText() {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        // Usar o prefixo 'this@BoxWithConstraints' remove o erro de "scope not used"
        val larguraDisponivel = this@BoxWithConstraints.maxWidth

        val textSize = if (larguraDisponivel < 600.dp) 16.sp else 24.sp

        Text(
            text = "Responsive Text",
            fontSize = textSize,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun OrientationAwareLayout(){
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(modifier = Modifier.fillMaxSize()) {
            Text(text = "Landscape Mode", fontSize = 24.sp)

        }
    }else{
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text("Portrait Layout")
            }
        }
    }

@Composable
fun WeightLayout(){
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Left", modifier = Modifier.weight(1f))
        Text("Right", modifier = Modifier.weight(2f))
    }
}

@Composable
fun AccessibleButton(){
    Button(
        onClick = { /* Ação do botão */ },
        modifier = Modifier.semantics{contentDescription = "Submit form"}
        ) {
        Text("Submit", fontSize = 16.sp)
    }

}


@Preview(device = Devices.PIXEL_4)
@Preview(device = "spec:width=1280dp,height=800dp,dpi=240")
@Composable
fun ResponsiveTextPreview() {
    AccessibleButton()
}



@Preview
@Composable
fun WeightLayoutPreview() {
    WeightLayout()
}


@Preview
@Composable
fun OrientationAwareLayoutPreview() {
    OrientationAwareLayout()
}