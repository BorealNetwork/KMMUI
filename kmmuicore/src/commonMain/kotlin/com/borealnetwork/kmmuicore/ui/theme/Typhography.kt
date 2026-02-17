package com.borealnetwork.kmmuicore.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import io.github.baudelioandalon.kmmuicore.drawable.Res
import io.github.baudelioandalon.kmmuicore.drawable.montserrat_bold
import io.github.baudelioandalon.kmmuicore.drawable.montserrat_light
import io.github.baudelioandalon.kmmuicore.drawable.montserrat_medium
import io.github.baudelioandalon.kmmuicore.drawable.montserrat_regular
import io.github.baudelioandalon.kmmuicore.drawable.montserrat_semibold
import io.github.baudelioandalon.kmmuicore.drawable.montserrat_thin
import io.github.baudelioandalon.kmmuicore.drawable.onest_bold
import io.github.baudelioandalon.kmmuicore.drawable.onest_light
import io.github.baudelioandalon.kmmuicore.drawable.onest_medium
import io.github.baudelioandalon.kmmuicore.drawable.onest_regular
import io.github.baudelioandalon.kmmuicore.drawable.onest_semibold
import io.github.baudelioandalon.kmmuicore.drawable.roboto_bold
import io.github.baudelioandalon.kmmuicore.drawable.roboto_light
import io.github.baudelioandalon.kmmuicore.drawable.roboto_medium
import io.github.baudelioandalon.kmmuicore.drawable.roboto_regular
import io.github.baudelioandalon.kmmuicore.drawable.roboto_semibold
import io.github.baudelioandalon.kmmuicore.drawable.roboto_thin
import io.github.baudelioandalon.kmmuicore.drawable.onest_thin
import org.jetbrains.compose.resources.Font

@Composable
fun robotoFamily() = FontFamily(
    Font(Res.font.roboto_thin, FontWeight.Thin),
    Font(Res.font.roboto_light, FontWeight.Light),
    Font(Res.font.roboto_regular, FontWeight.Normal),
    Font(Res.font.roboto_medium, FontWeight.Medium),
    Font(Res.font.roboto_semibold, FontWeight.SemiBold),
    Font(Res.font.roboto_bold, FontWeight.Bold)
)


@Composable
fun onestFamily() = FontFamily(
    Font(Res.font.onest_thin, FontWeight.Thin),
    Font(Res.font.onest_light, FontWeight.Light),
    Font(Res.font.onest_regular, FontWeight.Normal),
    Font(Res.font.onest_medium, FontWeight.Medium),
    Font(Res.font.onest_semibold, FontWeight.SemiBold),
    Font(Res.font.onest_bold, FontWeight.Bold)
)

@Composable
fun montserratFamily() = FontFamily(
    Font(Res.font.montserrat_thin, FontWeight.Thin),
    Font(Res.font.montserrat_light, FontWeight.Light),
    Font(Res.font.montserrat_regular, FontWeight.Normal),
    Font(Res.font.montserrat_medium, FontWeight.Medium),
    Font(Res.font.montserrat_semibold, FontWeight.SemiBold),
    Font(Res.font.montserrat_bold, FontWeight.Bold)
)

@Composable
fun appTypography(defaultFontFamily: FontFamily = robotoFamily()) = defaultFontFamily