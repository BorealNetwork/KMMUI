package com.borealnetwork.kmmui.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

//
//import allenmultiplatform.allensharedui.generated.resources.Res
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.layout.wrapContentWidth
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.CornerSize
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Button
//import androidx.compose.material.ButtonDefaults
//import androidx.compose.material.Card
//import androidx.compose.material.CircularProgressIndicator
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.Icon
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.TextUnit
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.borealnetwork.allensharedui.theme.BlueTransparent
//import com.borealnetwork.allensharedui.theme.FavoriteSelectedColor
//import com.borealnetwork.allensharedui.theme.FavoriteUnselectedColor
//import com.borealnetwork.allensharedui.theme.GrayBackgroundDrawerDismiss
//import com.borealnetwork.allensharedui.theme.GrayBorder
//import com.borealnetwork.allensharedui.theme.GrayLetterSeeAll
//import com.borealnetwork.allensharedui.theme.GreenStrong
//import com.borealnetwork.allensharedui.theme.GreenTransparent
//import com.borealnetwork.allensharedui.theme.StarColor
//import com.borealnetwork.shared.tools.drawColoredShadow
//import io.kamel.image.KamelImage
//import io.kamel.image.asyncPainterResource
//import org.jetbrains.compose.resources.DrawableResource
//import org.jetbrains.compose.resources.InternalResourceApi
//import org.jetbrains.compose.resources.ResourceItem
//import org.jetbrains.compose.resources.painterResource
//
////@OptIn(ExperimentalMaterialApi::class)
////@Composable
////fun FavoriteButton(modifier: Modifier = Modifier, clicked: (() -> Unit)? = null) {
////    Card(modifier = modifier.size(35.dp),
////        elevation = 0.dp,
////        shape = CircleShape,
////        onClick = { clicked?.invoke() }) {
////        Image(
////            modifier = Modifier.fillMaxSize(),
////            painter = painterResource(
////                resource = Res.drawable.ic_favorite_unselected
////            ),
////            contentDescription = ""
////        )
////    }
////}
//
//@OptIn(ExperimentalMaterialApi::class, InternalResourceApi::class)
//@Composable
//fun FavoriteCounterButton(
//    modifier: Modifier = Modifier,
//    amount: Int = 0, clicked: (() -> Unit)? = null
//) {
//    Card(
//        modifier = modifier
//            .height(35.dp)
//            .wrapContentWidth(),
//        elevation = 0.dp,
//        shape = RoundedCornerShape(10.dp),
//        backgroundColor = FavoriteUnselectedColor,
//        onClick = { clicked?.invoke() }) {
//        Row(
//            modifier = Modifier.wrapContentWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Image(
//                modifier = Modifier.size(35.dp),
//                painter = painterResource(
//                    resource = Res.drawable
//                ),
//                contentDescription = ""
//            )
//            BoldText(
//                modifier = Modifier
//                    .padding(end = 13.dp)
//                    .wrapContentWidth(),
//                text = "$amount",
//                color = FavoriteSelectedColor,
//                fontSize = 12.sp,
//                textAlign = TextAlign.Start
//            )
//        }
//
//    }
//}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun ShareButton(modifier: Modifier = Modifier, clicked: (() -> Unit)? = null) {
//    Card(
//        modifier = modifier
//            .size(35.dp),
//        elevation = 0.dp,
//        shape = CircleShape, onClick = { clicked?.invoke() }) {
//        Image(
//            modifier = Modifier.fillMaxSize(),
//            painter = painterResource(resource = Res.drawable.ic_share_icon),
//            contentDescription = ""
//        )
//    }
//}
//
//@Composable
//fun StartIcon(
//    modifier: Modifier = Modifier,
//    categoryImg: String,
//    topText: String, bottomText: String
//) {
//    Row(
//        modifier = modifier.wrapContentHeight().clickable { },
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        KamelImage(
//            modifier = Modifier.size(54.dp).background(Color.White),
//            resource = asyncPainterResource(data = categoryImg),
//            onLoading = { progress ->
//                CircularProgressIndicator(progress)
//            },
//            contentScale = ContentScale.Crop,
//            contentDescription = "category",
//        )
//        Column(
//            modifier = Modifier.padding(start = 19.dp).weight(1f)
//        ) {
//            MediumText(
//                text = topText, color = GrayLetterSeeAll, fontSize = 15.sp
//            )
//            BoldText(text = bottomText, fontSize = 15.sp, color = Color.Black)
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterialApi::class, InternalResourceApi::class)
//@Composable
//fun RightRoundedButton(modifier: Modifier = Modifier, clicked: (() -> Unit)) {
//    Box(
//        modifier = modifier
//            .size(52.dp)
//            .padding(bottom = 4.dp),
//        contentAlignment = Alignment.Center
//    ) {
//        Card(
//            modifier = Modifier
//                .size(40.dp),
//            elevation = 0.dp,
//            shape = CircleShape, onClick = { clicked?.invoke() }) {
//            Image(
//                modifier = Modifier.fillMaxSize(),
//                painter = painterResource(
//                    resource = Res.drawable.ic_rounded_arrow_right
//                ),
//                contentDescription = ""
//            )
//        }
//    }
//}
//
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun GrayButton(
//    modifier: Modifier = Modifier,
//    text: String = "Calificar",
//    onClick: (() -> Unit)? = null
//) {
//    Card(
//        modifier = modifier
//            .wrapContentWidth(),
//        elevation = 0.dp,
//        shape = RoundedCornerShape(10.dp),
//        backgroundColor = GrayBackgroundDrawerDismiss,
//        onClick = { onClick?.invoke() }
//    ) {
//        Row(modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)) {
//            SemiBoldText(
//                modifier = Modifier.fillMaxWidth(),
//                text = text,
//                fontSize = 13.sp,
//                textAlign = TextAlign.Center
//            )
//        }
//    }
//}
//
//
//@OptIn(ExperimentalMaterialApi::class, InternalResourceApi::class)
//@Composable
//fun MinimumAddButton(
//    modifier: Modifier = Modifier,
//    onClick: (() -> Unit)? = null
//) {
//    Card(
//        modifier = modifier
//            .drawColoredShadow(
//                color = GreenTransparent, alpha = 1f, borderRadius = 10.dp,
//                offsetY = 3.dp, offsetX = 3.dp, blurRadius = 10.dp
//            )
//            .size(30.dp),
//        backgroundColor = GreenStrong,
//        shape = RoundedCornerShape(3.dp),
//        onClick = {
//            onClick?.invoke()
//        }
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Icon(
//                modifier = Modifier.size(13.dp),
//                painter = painterResource(
//                    resource = Res.drawable.ic_more_icon
//                ),
//                contentDescription = "moreIcon",
//                tint = Color.White
//            )
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterialApi::class, InternalResourceApi::class)
//@Composable
//fun LittleAddButton(
//    modifier: Modifier = Modifier,
//    onClick: (() -> Unit)? = null
//) {
//    Card(
//        modifier = modifier
//            .drawColoredShadow(
//                color = GreenTransparent, alpha = 1f, borderRadius = 10.dp,
//                offsetY = 3.dp, offsetX = 3.dp, blurRadius = 10.dp
//            )
//            .size(40.dp),
//        backgroundColor = GreenStrong,
//        shape = RoundedCornerShape(3.dp),
//        onClick = {
//            onClick?.invoke()
//        }
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Icon(
//                modifier = Modifier.size(15.dp),
//                painter = painterResource(
//                    resource = Res.drawable.ic_more_icon
//                ),
//                contentDescription = "moreIcon",
//                tint = Color.White
//            )
//        }
//    }
//}
//
//@Composable
//fun ShadowButton(
//    modifier: Modifier = Modifier,
//    text: String = "Agregar al carrito",
////    @StringRes labelId: Int? = null,
//    size: TextUnit = 20.sp,
//    onClick: (() -> Unit)? = null
//) {
//    BlueButton(
//        modifier = modifier
//            .drawColoredShadow(
//                color = BlueTransparent, alpha = 1f, borderRadius = 10.dp,
//                offsetY = 6.dp, offsetX = 5.dp, blurRadius = 10.dp
//            ),
//        fontSize = size,
//        text = text,// ?: stringResource(id = labelId ?: R.string.empty_string),
//        borderRadius = 10.dp
//    ) {
//        onClick?.invoke()
//    }
//}
//
//@Composable
//fun StarRatingSelector(
//    modifier: Modifier = Modifier,
//    stars: Double
//) {
//    Row(
//        modifier = modifier
//            .wrapContentWidth(),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        repeat(
//            times = stars.toInt()
//        ) {
//            Icon(
//                modifier = Modifier
//                    .clickable { }
//                    .size(30.dp),
//                painter = painterResource(
//                    resource = Res.drawable.ic_star_icon
//                ),
//                contentDescription = "star one",
//                tint = StarColor
//            )
//        }
//    }
//}
//


@Preview
@Composable
fun ButtonsPreview() {
    Column {
        PrimaryButton(
            modifier = Modifier.padding(top = 25.dp),
            text = "RECUPERAR CONTRASEÑA"
        ) {

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
//
//@Composable
//fun CornerButton(
//    modifier: Modifier = Modifier,
//    text: String,//? = null,
////    @StringRes labelId: Int? = null,
//    onClick: (() -> Unit)? = null
//) {
//    Card(
//        modifier = modifier
//            .fillMaxWidth(), elevation = 0.dp,
//        border = BorderStroke(1.dp, GrayBorder)
//    ) {
//        Button(
//            modifier = Modifier
//                .background(color = Color.White)
//                .height(54.dp),
//            elevation = ButtonDefaults.elevation(
//                defaultElevation = 0.dp,
//                pressedElevation = 5.dp,
//                disabledElevation = 0.dp
//            ),
//            shape = RoundedCornerShape(corner = CornerSize(5.dp)),
//            onClick = { onClick?.invoke() },
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
//        ) {
//            SemiBoldText(
//                text = text,// ?: stringResource(id = labelId ?: R.string.empty_string),
//                fontSize = 15.sp,
//                color = Color.Black,
//                letterSpacing = 0.sp,
//                textAlign = TextAlign.Center
//            )
//        }
//    }
//}
//
//@OptIn(InternalResourceApi::class, InternalResourceApi::class, InternalResourceApi::class)
//@Composable
//fun CornerImgButton(
//    modifier: Modifier = Modifier,
//    text: String,
//    imgRes: String = "ic_google_logo",
//    onClick: (() -> Unit)? = null
//) {
//    Card(
//        modifier = modifier
//            .fillMaxWidth(), elevation = 0.dp,
//        border = BorderStroke(1.dp, GrayBorder)
//    ) {
//        Button(
//            modifier = Modifier
//                .background(color = Color.White)
//                .height(54.dp),
//            elevation = ButtonDefaults.elevation(
//                defaultElevation = 0.dp,
//                pressedElevation = 5.dp,
//                disabledElevation = 0.dp
//            ),
//            shape = RoundedCornerShape(corner = CornerSize(5.dp)),
//            onClick = { onClick?.invoke() },
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceAround
//            ) {
//                Image(
//                    painter = painterResource(
//                        resource = DrawableResource(
//                            "drawable:${imgRes}",
//                            setOf(
//                                ResourceItem(
//                                    setOf(),
//                                    "drawable/${imgRes}.xml", -1, -1
//                                ),
//                            )
//                        )
//                    ),
//                    contentDescription = "Login"
//                )
//                SemiBoldText(
//                    text = text,// ?: stringResource(id = labelId ?: R.string.empty_string),
//                    fontSize = 15.sp,
//                    color = Color.Black,
//                    letterSpacing = 0.sp,
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun ScannerButton(
//    modifier: Modifier = Modifier,
//    text: String,//? = null,
////    @StringRes labelId: Int? = null,
//    onClick: (() -> Unit)? = null
//) {
//    Card(
//        modifier = modifier
//            .fillMaxWidth(), elevation = 0.dp,
//        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
//    ) {
//        Button(
//            modifier = Modifier
//                .background(color = Color.White)
//                .height(54.dp),
//            elevation = ButtonDefaults.elevation(
//                defaultElevation = 0.dp,
//                pressedElevation = 5.dp,
//                disabledElevation = 0.dp
//            ),
//            shape = RoundedCornerShape(corner = CornerSize(5.dp)),
//            onClick = { onClick?.invoke() },
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
//        ) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Icon(
//                    modifier = Modifier.padding(end = 10.dp),
//                    painter = painterResource(resource = Res.drawable.bar_code),
//                    contentDescription = "bar code"
//                )
//                Text(
//                    text = text,// ?: stringResource(id = labelId ?: R.string.empty_string),
//                    fontSize = 15.sp,
//                    color = MaterialTheme.colors.primary,
//                    fontWeight = SemiBold,
//                    letterSpacing = 0.sp,
//                    textAlign = TextAlign.Center,
//                    fontFamily = MaterialTheme.typography.caption.fontFamily
//                )
//                Icon(
//                    modifier = Modifier.padding(start = 10.dp),
//                    painter = painterResource(
//                        resource = Res.drawable.qr_code
//                    ),
//                    contentDescription = "qr code"
//                )
//            }
//        }
//    }
//}