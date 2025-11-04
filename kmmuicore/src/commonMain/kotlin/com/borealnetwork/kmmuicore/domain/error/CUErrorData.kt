package com.borealnetwork.kmmuicore.domain.error

enum class CUErrorData(val code: String, val message: String) {
    ERROR_DEFAULT("E000", "Error desconocido"),
    ERROR_SEED("E001", "Semilla inválida"),
    ERROR_NETWORK("E002", "Error de red"),
    ERROR_TIMEOUT("E003", "Tiempo de espera agotado"),
    ERROR_UNAUTHORIZED("E004", "No autorizado"),
    ERROR_EMAIL_PSW("E005", "Correo y/o contraseña incorrectos"),
    ERROR_INVALID("E006", "Correo invalido"),
    ERROR_LOGIN("E008", "Login invalido"),
}