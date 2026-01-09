package com.borealnetwork.kmmuicore.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.borealnetwork.kmmuicore.domain.sources_files.PhotoSource

@Composable
fun PhotoSourceDialog(
    onDismissRequest: () -> Unit = {},
    onOptionSelected: (PhotoSource) -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = "Seleccionar origen")
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                OptionRow(
                    modifier = Modifier.padding(bottom = 8.dp),
                    icon = Icons.Default.PhotoCamera,
                    label = "Cámara",
                    onClick = {
                        onOptionSelected(PhotoSource.Camera)
                        onDismissRequest()
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                OptionRow(
                    icon = Icons.Default.PhotoLibrary,
                    label = "Galería",
                    onClick = {
                        onOptionSelected(PhotoSource.Gallery)
                        onDismissRequest()
                    }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cerrar")
            }
        }
    )
}

@Composable
private fun OptionRow(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(top = 10.dp, start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

