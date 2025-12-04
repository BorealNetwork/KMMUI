package com.borealnetwork.kmmuicore.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview



@Preview
@Composable
fun ButtonsPreview() {
    Column {
        PrimaryButton(
            modifier = Modifier.padding(top = 25.dp),
            text = "RECUPERAR CONTRASEÑA"
        ) {

        }

        SecondaryButton {

        }
    }
}

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String = "text",
    borderRadius: Dp = 12.dp,
    enabled: Boolean = true,
    textAlign: TextAlign = TextAlign.Center,
    onClick: () -> Unit = {}
) {

    val backgroundColor = if (enabled) MaterialTheme.colorScheme.primary else Color.Transparent
    val contentColor = if (enabled) White else Color.DarkGray
    val borderColor =
        if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary

    Surface(
        shape = RoundedCornerShape(borderRadius),
        color = backgroundColor,
        border = BorderStroke(1.dp, borderColor),
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(horizontal = 4.dp, vertical = 5.dp)
            .clickable {
                if (!enabled) {
                    return@clickable
                } else {
                    onClick()
                }
            }
    ) {
        Text(
            text = text,
            color = contentColor,
            maxLines = 1,
            fontSize = 14.sp,
            textAlign = textAlign,
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(align = Alignment.CenterVertically)
        )
    }

}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String = "text",
    borderRadius: Dp = 8.dp,
    height: Dp = 48.dp,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val backgroundColor = if (enabled) Color.Transparent else MaterialTheme.colorScheme.primary
    val borderColor =
        if (enabled) MaterialTheme.colorScheme.primary else DarkGray
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .border(
                width = 2.dp,
                color = borderColor,      // Azul similar
                shape = RoundedCornerShape(borderRadius) // Esquinas redondeadas
            )
            .background(
                color = backgroundColor,        // Fondo gris muy claro
                shape = RoundedCornerShape(borderRadius)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color(0xFF0A3A68),
            fontWeight = FontWeight.Bold
        )
    }

}