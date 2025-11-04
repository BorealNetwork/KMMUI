package com.borealnetwork.kmmuicore.utils

import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter

fun Color.toColorFilter(): ColorFilter = ColorFilter.tint(this, BlendMode.SrcIn)