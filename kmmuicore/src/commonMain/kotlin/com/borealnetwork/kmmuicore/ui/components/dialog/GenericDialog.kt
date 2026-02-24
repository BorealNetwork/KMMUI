package com.borealnetwork.kmmuicore.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.borealnetwork.kmmuicore.domain.dialog.DialogParams
import com.borealnetwork.kmmuicore.ui.components.ConfirmAndCancelButtons
import io.github.baudelioandalon.kmmuicore.drawable.Res
import io.github.baudelioandalon.kmmuicore.drawable.ic_close_item
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.TimeSource


@Composable
fun PreviewAlertDialog(params: DialogParams ) {
    QuestionAlertDialog(
        params = params
    )
    SessionAlertDialog(
        params = params
    )
}

@Composable
fun QuestionAlertDialog(
    params: DialogParams
) {
    if (params.timer != null) {
        LaunchedEffect(params.timer) {
            val timeSource = TimeSource.Monotonic
            val mark = timeSource.markNow()
            val duration = params.timer.milliseconds * 1000
            while (mark.elapsedNow() < duration) {
                val remaining = duration - mark.elapsedNow()
                println("Tiempo restante: ${remaining.inWholeMilliseconds} ms")
                delay(1000)
            }
            params.onDismiss()
        }
    }
    Dialog(
        onDismissRequest = params.onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false // Permite diseños a pantalla completa o anchos personalizados
        )
    ) {
        // Contenedor visual del diálogo
        Card(
            modifier = Modifier
                .fillMaxWidth(params.fractionWidth) // Controlas el ancho exacto
                .wrapContentHeight()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(White)
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().clickable {
                        params.onDismiss()
                    },
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_close_item),
                        contentDescription = "Warning Icon",
                        tint = Black
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    params.title, style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = params.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = {
                        params.onDismiss()
                    }) {
                        Text(
                            text = params.dismissText.orEmpty()
                        )
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = params.confirmColor
                        ),
                        onClick = {
                            params.onConfirm()
                        }) {
                        Text(
                            text = params.confirmText
                        )
                    }
                }
            }
        }
    }
}

// Color extraído del botón de la imagen
val ActionBlueColor = Color(0xFF0022DF)

@Composable
fun SessionAlertDialog(
    params: DialogParams
) {
    Dialog(
        onDismissRequest = params.onDismiss, // Qué pasa si tocan fuera del diálogo
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false // Permite controlar mejor los márgenes
        )
    ) {
        Surface(
            shape = RoundedCornerShape(24.dp), // Bordes bien redondeados
            color = White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp) // Margen con los bordes de la pantalla
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. Título
                Text(
                    text = params.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 2. Subtítulo / Descripción
                Text(
                    text = params.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF49454F), // Gris oscuro Material 3
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                if (params.showConfirmAndCancelButtons) {
                    // 3. Botón Principal (Confirmar)
                    ConfirmAndCancelButtons(
                        confirmText = params.confirmText,
                        onConfirm = params.onConfirm,
                        onDismiss = params.onDismiss,
                        dismissText = params.dismissText
                    )
                }

            }
        }
    }
}

@Composable
fun CustomAlertDialog(
    params: DialogParams,
    content: @Composable ColumnScope.() -> Unit
) {
    if (params.timer != null) {
        LaunchedEffect(params.timer) {
            val timeSource = TimeSource.Monotonic
            val mark = timeSource.markNow()
            params.timer.let {
                val duration = it.milliseconds * 1000
                while (mark.elapsedNow() < duration) {
                    val remaining = duration - mark.elapsedNow()
                    println("Tiempo restante: ${remaining.inWholeMilliseconds} ms")
                    delay(1000)
                }
            }
            params.onDismiss()
        }
    }
    Dialog(
        onDismissRequest = params.onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false // Permite diseños a pantalla completa o anchos personalizados
        )
    ) {
        // Contenedor visual del diálogo
        Card(
            modifier = Modifier
                .fillMaxWidth(params.fractionWidth) // Controlas el ancho exacto
                .wrapContentHeight()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(White)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    }
}