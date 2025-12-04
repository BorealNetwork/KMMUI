package com.borealnetwork.kmmuicore.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borealnetwork.kmmuicore.ui.theme.CardBackground

@Composable
fun OptionItem(
    icon: Painter,
    text: String,
    clicked: () -> Unit = {}
) {
    Card(
        modifier = Modifier.clickable { clicked() }, // Flotante sobre el fondo azul
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F8FF))
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFF6F7F9)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = Color(0xFF0D3B66),
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                BoldText(
                    text = text,
                    fontSize = 11.sp,
                    color = Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}

// Componente para los placeholders "+"
@Composable
fun AddPhotoPlaceholder(
    modifier: Modifier,
    containerSize: Dp = 110.dp,
    imagePainter: Painter,
    onImageClick: () -> Unit = {}
) {

    // Definimos el tamaño del icono de cerrar para calcular el offset
    val closeIconSize = 32.dp
    // El offset es la mitad del tamaño para que el centro del icono quede en la esquina
    val iconOffset = closeIconSize / 2

    // Usamos BOX para apilar elementos (Z-axis)
    Box(
        modifier = modifier.clickable { onImageClick() }
            // Agregamos padding al contenedor principal igual al offset.
            // Esto asegura que el icono que sobresale no sea cortado por layouts padres
            // y que el espacio total del componente incluya la parte que sobresale.
            .padding(start = iconOffset, top = iconOffset)
    ) {
        // 1. CAPA INFERIOR: El contenedor de la imagen (el cuadrado gris)
        Surface(
            modifier = Modifier
                .size(containerSize),
            shape = RoundedCornerShape(20.dp), // Esquinas bastante redondeadas según la imagen
            color = CardBackground
        ) {
            // Usamos otro Box para centrar la imagen dentro del Surface
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(12.dp) // Un poco de margen interno para la imagen
            ) {
                Image(
                    painter = imagePainter,
                    contentDescription = "Item image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(25.dp)
                )
            }
        }

    }
}