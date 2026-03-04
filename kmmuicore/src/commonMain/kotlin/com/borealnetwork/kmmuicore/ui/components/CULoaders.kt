package com.borealnetwork.kmmuicore.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.borealnetwork.kmmuicore.ui.theme.CUGrayColor
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ProgressLoader(isLoading: Boolean) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CUGrayColor)
                .clickable(enabled = false) {},
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun LoadingDialog(
    isLoading: Pair<Boolean, String>? = null,
    onDismissRequest: () -> Unit = {}
) {
    if (isLoading != null && isLoading.first) {
        // Utilizamos Dialog para superponer la vista y bloquear la pantalla trasera
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                dismissOnBackPress = false, // Evita que se cierre al presionar "Atrás"
                dismissOnClickOutside = false // Evita que se cierre al tocar fuera
            )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // 1. Indicador de carga circular grueso y blanco
                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 6.dp,
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = isLoading.second,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}