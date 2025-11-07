package com.borealnetwork.kmmuicore.ui.theme

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import io.github.baudelioandalon.kmmuicore.generated.resources.Res
import io.github.baudelioandalon.kmmuicore.generated.resources.roboto
import io.github.baudelioandalon.kmmuicore.generated.resources.roboto_bold
import io.github.baudelioandalon.kmmuicore.generated.resources.roboto_light

@Composable
fun robotoFamily() = FontFamily(
    Font(Res.font.roboto_light, FontWeight.Light),
    Font(Res.font.roboto, FontWeight.Normal),
    Font(Res.font.roboto, FontWeight.SemiBold), // Puedes usar Medium o Bold si no tienes un archivo SemiBold
    Font(Res.font.roboto_bold, FontWeight.Bold)
)

@Composable
fun appTypography() = robotoFamily()