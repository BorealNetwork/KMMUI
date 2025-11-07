package com.borealnetwork.kmmuicore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.baudelioandalon.kmmuicore.generated.resources.Res
import io.github.baudelioandalon.kmmuicore.generated.resources.ic_arrow_back
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ShowPreviewIcon() {
    Column {
        CircularIcon()
        CircularImage()
    }
}

@Composable
fun CircularIcon(
    modifier: Modifier = Modifier,
    icon: DrawableResource = Res.drawable.ic_arrow_back,
    contentDescription: String? = null,
    iconTint: Color = White,
    sizeContent: Dp = 62.dp,
    sizeIcon: Dp = 45.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .size(sizeContent),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .size(sizeIcon),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor // Cambia este color según necesites
            ),
            onClick = { onClick?.invoke() }) {
            Icon(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(5.dp),
                painter = painterResource(resource = icon),
                tint = iconTint,
                contentDescription = contentDescription
            )
        }
    }
}

@Composable
fun CircularImage(
    modifier: Modifier = Modifier,
    icon: DrawableResource = Res.drawable.ic_arrow_back,
    iconTint: ColorFilter? = null,
    contentSize: Dp = 62.dp,
    size: Dp = 45.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .size(contentSize),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .size(size),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor // Cambia este color según necesites
            ),
            onClick = { onClick?.invoke() }) {
            Image(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(5.dp),
                painter = painterResource(resource = icon),
                contentDescription = "",
                colorFilter = iconTint
            )
        }
    }
}


