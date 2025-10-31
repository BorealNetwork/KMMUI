package com.secam.inventarios.network.core

sealed class CIErrorType<out T> {

    data class BackendError(val code: String, val message: String, val data: Any?) :
        CIErrorType<Nothing>()

    data class HttpError(val code: Int, val message: String, val errorBody: String? = null) :
        CIErrorType<Nothing>()

    data class NetworkError(val exception: Throwable) : CIErrorType<Nothing>()

    data class SerializationError(val throwable: Throwable, val rawBody: String?) :
        CIErrorType<Nothing>()

    data class UnknownError(val throwable: Throwable) : CIErrorType<Nothing>()
}
 