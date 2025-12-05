package com.borealnetwork.kmmuicore.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borealnetwork.kmmuicore.ui.theme.CUDisabledColor
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
    borderRadius: Dp = 8.dp,
    backgroundEnabledColor: Color = MaterialTheme.colorScheme.primary,
    backgroundDisabledColor: Color = CUDisabledColor,
    textEnabledColor: Color = White,
    textDisabledColor: Color = DarkGray,
    borderEnabledColor: Color = MaterialTheme.colorScheme.primary,
    borderDisabledColor: Color = CUDisabledColor,
    fontSize: TextUnit = 13.sp,
    height: Dp = 48.dp,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val backgroundColor = if (enabled) backgroundEnabledColor else backgroundDisabledColor
    val textColor = if (enabled) textEnabledColor else textDisabledColor
    val borderColor =
        if (enabled) borderEnabledColor else borderDisabledColor
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
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        BoldText(
            text = text,
            color = textColor,
            fontSize = fontSize
        )
    }

}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String = "text",
    borderRadius: Dp = 8.dp,
    backgroundEnabledColor: Color = Color.Transparent,
    backgroundDisabledColor: Color = CUDisabledColor,
    textEnabledColor: Color = MaterialTheme.colorScheme.primary,
    textDisabledColor: Color = DarkGray,
    borderEnabledColor: Color = MaterialTheme.colorScheme.primary,
    borderDisabledColor: Color = CUDisabledColor,
    fontSize: TextUnit = 13.sp,
    height: Dp = 48.dp,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val backgroundColor = if (enabled) backgroundEnabledColor else backgroundDisabledColor
    val textColor = if (enabled) textEnabledColor else textDisabledColor
    val borderColor =
        if (enabled) borderEnabledColor else borderDisabledColor
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
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        BoldText(
            text = text,
            color = textColor,
            fontSize = fontSize
        )
    }

}
