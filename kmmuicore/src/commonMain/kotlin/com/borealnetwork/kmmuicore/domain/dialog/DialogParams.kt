package com.borealnetwork.kmmuicore.domain.dialog

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import com.borealnetwork.kmmuicore.domain.base.EMPTY_STRING
import com.borealnetwork.kmmuicore.ui.theme.PrimaryColor
import com.borealnetwork.kmmuicore.utils.toAnnotateString
import io.github.baudelioandalon.kmmuicore.drawable.Res
import io.github.baudelioandalon.kmmuicore.drawable.ic_close_item
import io.github.baudelioandalon.kmmuicore.drawable.ic_help
import org.jetbrains.compose.resources.DrawableResource

data class DialogParams(
    val titleScreen: String = "Screen name",
    val title: String = EMPTY_STRING,
    val date: String = EMPTY_STRING,
    val status: String = EMPTY_STRING,
    val fullScreen: Boolean = false,
    val onClickOutside: Boolean = false,
    val onBackPress: Boolean = false,
    val showConfirmAndCancelButtons: Boolean = true,
    val showCloseButton: Boolean = false,
    val icon: DrawableResource = Res.drawable.ic_close_item,
    val note: String? = null,
    val description: AnnotatedString = AnnotatedString(EMPTY_STRING),
    val confirmText: String = "Aceptar",
    val dismissText: String? = "Cancelar",
    val timer: Int? = null,
    val fractionWidth: Float = 0.9f,
    val confirmColor: Color = PrimaryColor,
    val onConfirm: () -> Unit = {},
    val onDismiss: () -> Unit = {}
) {
    companion object {
        val EMPTY_DIALOG = DialogParams()
        val ERROR_DEFAULT_DIALOG = DialogParams(
            title = "Error",
            description = "Ha ocurrido un error inesperado".toAnnotateString()
        )
        val LOCATION_PERMISSION_DIALOG = DialogParams(
            icon = Res.drawable.ic_help,
            titleScreen = "Acceso a localización",
            title = "Dar acceso a localización",
            description = ("Para prospectar clientes y optimizar tu experiencia, " +
                    "necesitamos acceder a tu ubicación. Esto nos permite ofrecerte información " +
                    "relevante. Para que podamos acceder a la ubicación, solo ve a la configuración " +
                    "de tu teléfono, busca \"aplicaciones\", elige [Nombre de la app], toca" +
                    " \"permisos\" y activa la opción de \"ubicación\".").toAnnotateString(),
            confirmText = "Ir a configuración"
        )

        fun successDialog(message: String, timer: Int? = null) = DialogParams(
            title = "Exito",
            timer = timer,
            description = message.toAnnotateString()
        )

        fun errorDialog(error: String, timer: Int? = null) = DialogParams(
            title = "Error",
            confirmText = "OK",
            timer = timer,
            description = error.toAnnotateString(),
            dismissText = EMPTY_STRING
        )

        val HIDE_DIALOG = Pair(false, EMPTY_DIALOG)
    }

    fun build(
        onConfirm: () -> Unit = {},
        onDismiss: () -> Unit = {}
    ) = Pair(
        true,
        copy(
            onDismiss = onDismiss,
            onConfirm = onConfirm
        )
    )

    fun build() = Pair(
        true,
        this.copy()
    )

}
