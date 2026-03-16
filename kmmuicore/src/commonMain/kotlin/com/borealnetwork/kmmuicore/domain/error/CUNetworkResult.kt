package com.borealnetwork.kmmuicore.domain.error

sealed class CUNetworkResult<out T> {

    data class Success<T>(val code: String, val message: String, val data: T?) :
        CUNetworkResult<T>()

    /**
     * Representa un error controlado por el backend
     *
     * @param code String código del error
     * @param message String mensaje del error
     * @param data Any? datos adicionales del error (puede ser nulo)
     *  @returns TCIErrorType<Nothing>
     *  */
    data class BackendError(val code: String, val message: String, val data: Any?) :
        CUNetworkResult<Nothing>()

    /**
     * Representa un error HTTP no controlado por el backend
     *  @param code Int código HTTP del error
     * @param message String mensaje del error
     * @param errorBody String? cuerpo del error (puede ser nulo)
     *  @returns TCIErrorType<Nothing>
     * */
    data class HttpError(val code: Int, val message: String, val errorBody: String? = null) :
        CUNetworkResult<Nothing>()

    /**
     * Representa un error de red (como problemas de conexión)
     * @param exception Throwable excepción que causó el error de red
     *  @returns TCIErrorType<Nothing>
     * */
    data class NetworkError(val exception: Throwable) : CUNetworkResult<Nothing>()

    /**
     * Representa un error de serialización/deserialización
     * @param throwable Throwable excepción que causó el error de serialización
     * @param rawBody String? cuerpo bruto que causó el error (puede ser nulo)
     *  @returns TCIErrorType<Nothing>
     * */
    data class SerializationError(val throwable: Throwable, val rawBody: String?) :
        CUNetworkResult<Nothing>()

    /**
     * Representa un error desconocido o no categorizado
     * @param throwable Throwable excepción que causó el error desconocido
     *  @returns TCIErrorType<Nothing>
     * */
    data class UnknownError(val throwable: Throwable) : CUNetworkResult<Nothing>()
    object Idle : CUNetworkResult<Nothing>()
}
 