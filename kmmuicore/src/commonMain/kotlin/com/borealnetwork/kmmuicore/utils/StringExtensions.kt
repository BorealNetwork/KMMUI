package com.borealnetwork.kmmuicore.utils

import com.borealnetwork.kmmuicore.domain.base.EMPTY_STRING
import com.borealnetwork.kmmuicore.domain.base.specialChars

fun String.containsAlphanumericCharacters() = matches(Regex("^[A-Za-z0-9? ,_-]+\$"))
fun String.isDigitsOnly() = matches(Regex("[0-9]"))

/**
 * Valida una contraseña y devuelve un mensaje de error si no es válida.
 *
 * @param password La contraseña a validar.
 * @return `[EMPTY_STRING]` si la contraseña es válida, o un `String` con el mensaje de error.
 */
fun String.validatePassword(extraValidation: String = EMPTY_STRING): String {
    if (this.isEmpty()) {
        return "La contraseña no puede ser vacia"
    }

    if (this != extraValidation && extraValidation.isNotEmpty()) {
        return "Las contraseñas no coinciden"
    }
    // Regla 1: Mínimo 8 caracteres
    if (length < 8) {
        return "La contraseña debe tener al menos 8 caracteres."
    }
    // Regla 2: Mayúsculas y minúsculas
    if (!any { it.isUpperCase() }) {
        return "Debe contener al menos una letra mayúscula."
    }
    if (!any { it.isLowerCase() }) {
        return "Debe contener al menos una letra minúscula."
    }
    // Regla 3: Al menos un número
    if (!any { it.isDigit() }) {
        return "Debe contener al menos un número."
    }
    // Regla 4: Al menos un carácter especial

    if (!any { it in specialChars }) {
        // Puedes personalizar la lista de caracteres especiales
        return "Debe contener al menos un carácter especial (ej: @, #, $, %)."
    }
    // Regla 5: No más de 3 caracteres iguales consecutivos
    if (windowed(4).any { it[0] == it[1] && it[1] == it[2] && it[2] == it[3] }) {
        return "No debe tener más de 3 caracteres iguales consecutivos (ej: aaaa, 1111)."
    }
    // Regla 5: No secuencias consecutivas (123, abc)
    for (i in 0..length - 3) {
        val char1 = this[i]
        val char2 = this[i + 1]
        val char3 = this[i + 2]

        // Secuencias numéricas ascendentes y descendentes (ej: 123, 321)
        if (char1.isDigit() && char2.isDigit() && char3.isDigit()) {
            if (char2.digitToInt() == char1.digitToInt() + 1 && char3.digitToInt() == char2.digitToInt() + 1) {
                return "No debe tener secuencias numéricas consecutivas (ej: 123)."
            }
        }
        // Secuencias alfabéticas ascendentes y descendentes (ej: abc, cba)
        if (char1.isLetter() && char2.isLetter() && char3.isLetter()) {
            // Se convierten a minúsculas para la comparación
            if (char2.lowercaseChar() == char1.lowercaseChar() + 1 && char3.lowercaseChar() == char2.lowercaseChar() + 1) {
                return "No debe tener secuencias alfabéticas consecutivas (ej: abc)."
            }
        }
    }

    return EMPTY_STRING // La contraseña es válida
}