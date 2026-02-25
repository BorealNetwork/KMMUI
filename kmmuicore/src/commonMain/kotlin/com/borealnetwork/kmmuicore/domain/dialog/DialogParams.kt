package com.borealnetwork.kmmuicore.domain.dialog

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import com.borealnetwork.kmmuicore.domain.base.EMPTY_STRING
import com.borealnetwork.kmmuicore.ui.theme.PrimaryColor
import com.borealnetwork.kmmuicore.utils.toAnnotateString

data class DialogParams(
    val title: String = EMPTY_STRING,
    val showConfirmAndCancelButtons: Boolean = true,
    val showCloseButton: Boolean = false,
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
