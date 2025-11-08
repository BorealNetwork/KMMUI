package com.borealnetwork.kmmuicore.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borealnetwork.kmmuicore.ui.theme.GrayLetterShipping
import io.github.baudelioandalon.kmmuicore.drawable.Res
import io.github.baudelioandalon.kmmuicore.drawable.ic_design_invisibility
import io.github.baudelioandalon.kmmuicore.drawable.ic_design_visibility
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

open class NavigationScreen(
    val route: String,
    val title: String,
    val icon: DrawableResource = Res.drawable.ic_design_visibility
)


@Composable
fun CustomBottomNavigation(
    currentScreenId: NavigationScreen,
    items: List<NavigationScreen>,
    onItemSelected: (Int) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
            focusedElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp)
                .height(80.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                CustomBottomNavigationItem(
                    item = item,
                    isSelected = item == currentScreenId
                ) {
                    onItemSelected(index)
                }
            }
        }
    }

}

@Composable
fun CustomBottomNavigationItem(
    item: NavigationScreen,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val background =
        if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else Color.Transparent
    val contentColor =
        if (isSelected) MaterialTheme.colorScheme.primary else GrayLetterShipping

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            painterResource(resource = Res.drawable.ic_design_invisibility)
            Icon(
                painter = painterResource(resource = item.icon),
                contentDescription = null,
                tint = contentColor
            )

            AnimatedVisibility(visible = isSelected) {
                BoldText(
                    text = item.title,
                    color = contentColor,
                    fontSize = 12.sp
                )
            }

        }
    }
}