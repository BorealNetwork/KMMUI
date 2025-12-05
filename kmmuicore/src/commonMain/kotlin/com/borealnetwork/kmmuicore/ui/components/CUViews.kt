package com.borealnetwork.kmmuicore.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borealnetwork.kmmuicore.ui.components.MediumText
import com.borealnetwork.kmmuicore.ui.components.RegularText
import com.borealnetwork.kmmuicore.ui.theme.CUGrayLetterHint
import com.borealnetwork.kmmuicore.ui.theme.GrayBorderLight


@Composable
fun SeparatorHorizontal(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = GrayBorderLight,
            thickness = 2.dp
        )
        MediumText(
            modifier = Modifier.wrapContentSize().padding(horizontal = 10.dp),
            text = "ó",
            fontSize = 20.sp,
            color = Black
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = GrayBorderLight,
            thickness = 2.dp
        )
    }
}

@Composable
fun SeparatorGray(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier, color = GrayBorderLight, thickness = 1.dp
    )
}

@Composable
fun ValidText(
    modifier: Modifier = Modifier,
    validColor: Color = Black,
    invalidColor: Color = CUGrayLetterHint,
    textSize: TextUnit = 15.sp,
    paddingStart: Dp = 8.dp,
    isValid: Boolean,
    text: String
) {
    val circleColor = if (isValid) Color.Green else Color.Gray

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Círculo a la izquierda
        Canvas(modifier = Modifier.size(9.dp)) {
            drawCircle(color = circleColor)
        }

        // Texto a la derecha
        MediumText(
            text = text,
            fontSize = textSize,
            color = if (isValid) validColor else invalidColor,
            modifier = Modifier.padding(start = paddingStart)
        )
    }
}