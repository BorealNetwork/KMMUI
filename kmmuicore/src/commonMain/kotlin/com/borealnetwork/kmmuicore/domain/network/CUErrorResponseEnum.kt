package com.borealnetwork.kmmuicore.domain.network

enum class CUErrorResponseEnum(
    val messageError: String,
    val defaultError: String,
    val errorCode: String
) {
    ERROR_SYSTEM(
        "Hubo un error de comunicación mientras se procesaba su petición, favor de reintentar.",
        "Hubo un error de comunicación mientras se procesaba su petición, favor de reintentar.",
        "-3"
    ),
    ERROR_DEFAULT(
        "Favor de verificar su conexion a internet.",
        "Favor de verificar su conexion a internet.",
        "-3"
    ),
    ERROR_AUTHORIZATION(
        "Favor de verificar su conexion a internet.",
        "Favor de verificar su conexion a internet.",
        "-3"
    ),
    AUTHORIZATION_ERROR(
        "Se requiere autorización. Credenciales no válidas",
        "Authorization Error",
        "401"
    ),
    UNSUPPORTED_MEDIA_TYPE_ERROR(
        "El tipo de contenido de la solicitud no es soportado por el servidor.",
        "Unsupported Media Type",
        "415"
    ),
    TOO_MANY_REQUESTS(
        "Demasiadas peticiones en un corto lapzo de tiempo, intenta más tarde.",
        "Too Many Requests",
        "429"
    ),
    ERROR_SIGNED_ON_ANOTHER_DEVICE(
        "Ha iniciado sesión en otro dispositivo",
        "Semilla expirada",
        "007"
    ),
    ERROR_CONEXION(
        "Tu conexion ha fallado",
        "Error conexion",
        "1012"
    ),
    ERROR_MALFORMED_JSON(
        "El modelo de respuesta es incorrecto",
        "Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 1 path",
        "1000"
    ),
    ERROR_TIMEOUT(
        "El servidor superó el tiempo de respuesta",
        "failed to connect to /10.112.167.166 (port 9081) from /192.168.2.194 (port 54152) after 10000ms",
        "504"
    ),
    ERROR_UNKNOWN_HOST(
        "No hay ningun host asociado, favor de validar la url",
        "Favor de verificar su conexion a internet.",
        "4000"
    ),
    ERROR_URL_NOT_FOUND(
        "La url de la peticion no existe, favor de validarla.", "Not Found", "404"
    ),
    ERROR_METHOD_NOT_ALLOWED(
        "La url no permite ese tipo de peticion", "Method Not Allowed", "405"
    ),
    ERROR_SERVICE_UNAVAILABLE(
        "El servicio no está disponible temporalmente",
        "Temporalmente fuera de servicio, intente mas tarde",
        "503"
    )
}