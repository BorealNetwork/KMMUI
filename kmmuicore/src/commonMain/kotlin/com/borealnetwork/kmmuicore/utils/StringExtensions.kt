package com.borealnetwork.kmmuicore.utils

import androidx.compose.ui.text.AnnotatedString
import com.borealnetwork.kmmuicore.domain.base.EMPTY_STRING


/**
 * Valida una contraseña y devuelve un mensaje de error si no es válida.
 *
 * @param password La contraseña a validar.
 * @return `[EMPTY_STRING]` si la contraseña es válida, o un `String` con el mensaje de error.
 */

fun String.log(key: String, error: Boolean = true) {
    if (!error) {
        println("$key: $this")
    } else {
        println("ERROR: $key: $this")
    }
}


fun String.containsAlphanumericCharacters() = matches(Regex("^[A-Za-z0-9? ,_-]+\$"))
fun String.isDigitsOnly() = matches(Regex("[0-9]"))


fun String.toAnnotateString() = AnnotatedString(this)