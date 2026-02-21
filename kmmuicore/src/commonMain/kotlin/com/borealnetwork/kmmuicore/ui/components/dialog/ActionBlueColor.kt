package com.borealnetwork.kmmuicore.ui.components.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

// Color extraído del botón de la imagen
val ActionBlueColor = Color(0xFF0022DF) 

@Composable
fun AlertDialog(
    title: String,
    description: String,
    confirmText: String = "Confirmar",
    dismissText: String = "Cancelar",
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss, // Qué pasa si tocan fuera del diálogo
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false // Permite controlar mejor los márgenes
        )
    ) {
        Surface(
            shape = RoundedCornerShape(24.dp), // Bordes bien redondeados
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp) // Margen con los bordes de la pantalla
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. Título
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 2. Subtítulo / Descripción
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF49454F), // Gris oscuro Material 3
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                // 3. Botón Principal (Confirmar)
                Button(
                    onClick = onConfirm,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp), // Altura un poco mayor para mejor área táctil
                    colors = ButtonDefaults.buttonColors(containerColor = ActionBlueColor),
                    shape = RoundedCornerShape(12.dp) // Bordes un poco más cuadrados que un "Stadium"
                ) {
                    Text(
                        text = confirmText,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 4. Botón Secundario (Regresar)
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = dismissText,
                        fontWeight = FontWeight.Bold,
                        color = ActionBlueColor
                    )
                }
            }
        }
    }
}