package com.borealnetwork.kmmuicore.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.font.FontWeight.Companion.W200
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.borealnetwork.kmmuicore.ui.theme.GrayLetterSeeAll
import com.borealnetwork.kmmuicore.ui.theme.appTypography


@Composable
fun SemiBoldText(
    modifier: Modifier = Modifier,
    text: String,
    letterSpacing: TextUnit = 0.sp,
    color: Color = Color.Black,
    textAlign: TextAlign? = null,
    fontSize: TextUnit = 18.sp,
    lineHeight: TextUnit = 14.sp,
    textOverflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1
) {

    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        letterSpacing = letterSpacing,
        color = color,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = textOverflow,
        maxLines = maxLines,
        minLines = minLines,
        fontWeight = SemiBold,
        fontFamily = appTypography()
    )
}

@Composable
fun BoldText(
    modifier: Modifier = Modifier,
    text: String,
    letterSpacing: TextUnit = 0.sp,
    fontSize: TextUnit = 25.sp,
    color: Color = Color.White,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = 14.sp,
    textOverflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    softWrap: Boolean = true,
    onClicked: (() -> Unit)? = null
) {
    Text(
        modifier = if (onClicked != null) modifier.clickable { onClicked.invoke() } else modifier,
        text = text,
        fontSize = fontSize,
        color = color,
        letterSpacing = letterSpacing,
        textAlign = textAlign,
        lineHeight = lineHeight,
        minLines = minLines,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        fontWeight = Bold,
        fontFamily = appTypography()
    )
}

@Composable
fun MediumText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    textAlign: TextAlign? = null,
    letterSpacing: TextUnit = 0.sp,
    fontSize: TextUnit = 20.sp,
    textOverflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        letterSpacing = letterSpacing,
        color = color,
        fontWeight = Medium,
        textAlign = textAlign,
        overflow = textOverflow,
        maxLines = maxLines,
        fontFamily = appTypography()
    )
}


@Composable
fun MediumTextBold(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 20.sp,
    color: Color = Color.Black,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        color = color,
        letterSpacing = 0.sp,
        textAlign = textAlign,
        fontWeight = Medium,
        fontFamily = appTypography()
    )
}


@Composable
fun RegularText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 15.sp,
    color: Color = Color.Black,
    letterSpacing: TextUnit = 0.sp,
    maxLines: Int = Int.MAX_VALUE,
    lineHeight: TextUnit = 14.sp,
    textOverflow: TextOverflow = TextOverflow.Clip,
    style: TextStyle = LocalTextStyle.current,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,// ?: stringResource(id = labelId ?: R.string.empty_string),
        fontSize = fontSize,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        maxLines = maxLines,
        overflow = textOverflow,
        textAlign = textAlign,
        style = style,
        fontWeight = Normal,
        fontFamily = appTypography()
    )
}

@Composable
fun LightText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 15.sp,
    color: Color = Color.Black,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,// ?: stringResource(id = labelId ?: R.string.empty_string),
        fontSize = fontSize,
        color = color,
        textAlign = textAlign,
        fontWeight = Light,
        fontFamily = appTypography()
    )
}

@Composable
fun SmallText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = GrayLetterSeeAll,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,// ?: stringResource(id = labelId ?: R.string.empty_string),
        fontSize = 12.sp,
        color = color,
        letterSpacing = 0.sp,
        textAlign = textAlign,
        fontWeight = W200,
        fontFamily = appTypography()
    )
}