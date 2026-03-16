package com.borealnetwork.kmmuicore.domain.error

enum class CUNetworkErrorsEnum(val code: String, val message: String) {
    MALFORMED_JSON_ERROR("", "Error al analizar la respuesta JSON."),
    AUTHORIZATION_ERROR("401", "Se requiere autorización. Credenciales no válidas. "),
    URL_NOT_FOUND_ERROR("404", "La url de la petición no existe, favor de validarla. "),
    UNSUPPORTED_MEDIA_TYPE_ERROR(
        "415",
        "El tipo de contenido de la solicitud no es soportado por el servidor."
    ),
    UNSUPPORTED_MEDIA_TYPE_ERROR_426(
        "426",
        "El tipo de contenido de la solicitud no es soportado por el servidor."
    ),
    TO_MANY_REQUESTS(
        "429",
        "Demasiadas peticiones en un corto lapzo de tiempo, intenta más tarde. "
    ),
    SERVER_ERROR_500("500", "Error del servidor."),
    SERVER_ERROR_502("502", "Bad Gateway."),
    SERVER_ERROR_503(
        "503",
        "La solicitud no puede ser completada en este momento, intenta más tarde. "
    ),
    TIMEOUT_ERROR("504", "El servidor superó el tiempo de respuesta, favor de reintentar. "),
    SIGNED_ON_ANOTHER_DEVICE("007", "Ha iniciado sesión en otro dispositivo. "),
    EXPIRED_SEED("018", "Seed expirado "),
    USER_NOT_ACTIVE("010", "El usuario no está Activo");
}

enum class TCUCodeCategory(val codes: List<String>) {
    SUCCESS(listOf("000", "171")),
    SESSION_CONFLICT(listOf("07", "007", "017", "018", "019", "020")),
    UNKNOWN(emptyList()); // Categoría por defecto (el 'else')

    companion object {
        /**
         * Busca la categoría a la que pertenece un código de texto.
         * Usa 'entries' (disponible en Kotlin 1.9+) en lugar de 'values()'.
         */
        fun fromCode(code: String): TCUCodeCategory {
            return entries.find { code in it.codes } ?: UNKNOWN
        }
    }
}