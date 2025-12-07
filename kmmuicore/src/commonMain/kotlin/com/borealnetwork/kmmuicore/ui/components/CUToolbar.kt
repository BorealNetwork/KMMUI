package com.borealnetwork.kmmuicore.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borealnetwork.kmmuicore.domain.base.EMPTY_STRING
import com.borealnetwork.kmmuicore.utils.toColorFilter
import io.github.baudelioandalon.kmmuicore.drawable.Res
import io.github.baudelioandalon.kmmuicore.drawable.ic_arrow_back
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun PreviewToolbar() {
    CUToolbarTitle(
        titleText = "Titulo",
        titleColor = MaterialTheme.colorScheme.primary,
        showStartImage = false,
        startClicked = {

        })
}

@Preview
@Composable
fun CUToolbarTitle(
    modifier: Modifier = Modifier,
    titleText: String = EMPTY_STRING,
    titleFontSize: TextUnit = 20.sp,
    showStartImage: Boolean = true,
    iconTint: ColorFilter? = MaterialTheme.colorScheme.primary.toColorFilter(),
    startClicked: (() -> Unit)? = null,
    endClicked: (() -> Unit)? = null,
    showEndImage: Boolean = false,
    iconStartSize: Dp = 100.dp,
    iconEndSize: Dp = 100.dp,
    helpButtonClicked: (() -> Unit)? = null,
    helpButton: Boolean = false,
    startIcon: DrawableResource = Res.drawable.ic_arrow_back,
    startIconTint: Color = White,
    endIconTint: ColorFilter? = MaterialTheme.colorScheme.primary.toColorFilter(),
    titleColor: Color = Black,
    endIcon: DrawableResource = Res.drawable.ic_arrow_back,
    backgroundColor: Color = White
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(backgroundColor)
    ) {
        if (showStartImage) {
            CircularImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(),
                iconTint = iconTint,
                backgroundColor = White,
                size = iconStartSize,
                icon = Res.drawable.ic_arrow_back
            ) {
                startClicked?.invoke()
            }
        }
        BoldText(
            modifier = Modifier.align(Alignment.Center),
            text = titleText,
            textAlign = TextAlign.Justify,
            fontSize = titleFontSize,
            color = titleColor
        )
        if (showEndImage) {
            CircularImage(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 5.dp),
                backgroundColor = White,
                iconTint = endIconTint,
                icon = endIcon
            ) {
                endClicked?.invoke()
            }
        }
        if (helpButton) {
            Surface(
                modifier = Modifier.align(Alignment.CenterEnd),
                color = MaterialTheme.colorScheme.primary
            ) {
                CircularImage(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(),
                    size = iconEndSize,
                    icon = Res.drawable.ic_arrow_back
                ) {
                    helpButtonClicked?.invoke()
                }
            }
        }
    }

}