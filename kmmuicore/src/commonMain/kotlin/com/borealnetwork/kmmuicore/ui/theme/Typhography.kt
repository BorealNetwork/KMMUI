package com.borealnetwork.kmmuicore.ui.theme

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import io.github.baudelioandalon.kmmuicore.drawable.Res
import io.github.baudelioandalon.kmmuicore.drawable.montserrat_bold
import io.github.baudelioandalon.kmmuicore.drawable.montserrat_regular
import io.github.baudelioandalon.kmmuicore.drawable.montserrat_semibold
import io.github.baudelioandalon.kmmuicore.drawable.montserrat_thin
import io.github.baudelioandalon.kmmuicore.drawable.roboto
import io.github.baudelioandalon.kmmuicore.drawable.roboto_bold
import io.github.baudelioandalon.kmmuicore.drawable.roboto_light

@Composable
fun robotoFamily() = FontFamily(
    Font(Res.font.roboto_light, FontWeight.Light),
    Font(Res.font.roboto, FontWeight.Normal),
    Font(Res.font.roboto, FontWeight.SemiBold), // Puedes usar Medium o Bold si no tienes un archivo SemiBold
    Font(Res.font.roboto_bold, FontWeight.Bold)
)
@Composable
fun montserratFamily() = FontFamily(
    Font(Res.font.montserrat_thin, FontWeight.Light),
    Font(Res.font.montserrat_regular, FontWeight.Normal),
    Font(Res.font.montserrat_semibold, FontWeight.SemiBold), // Puedes usar Medium o Bold si no tienes un archivo SemiBold
    Font(Res.font.montserrat_bold, FontWeight.Bold)
)

@Composable
fun appTypography(defaultFontFamily: FontFamily = robotoFamily()) = defaultFontFamily