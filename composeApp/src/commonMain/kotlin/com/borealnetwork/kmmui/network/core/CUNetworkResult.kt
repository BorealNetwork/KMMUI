package com.secam.inventarios.network.core


sealed class CUNetworkResult<out T> {

    data class Success<T>(val code: String, val message: String, val data: T?) :
        CUNetworkResult<T>()

    data class BackendError(val code: String, val message: String, val data: Any?) :
        CUNetworkResult<Nothing>()

    data class HttpError(val code: Int, val message: String, val errorBody: String? = null) :
        CUNetworkResult<Nothing>()

    data class NetworkError(val exception: Throwable) : CUNetworkResult<Nothing>()

    data class SerializationError(val throwable: Throwable, val rawBody: String?) :
        CUNetworkResult<Nothing>()
}
 