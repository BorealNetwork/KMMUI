package com.borealnetwork.kmmuicore.network.core

sealed class CUUiState<out T> {

    data class Success<T>(val data: T?) : CUUiState<T>()

    data class Error<T>(val message: String) : CUUiState<T>()

    object Loading : CUUiState<Nothing>()
    object None : CUUiState<Nothing>()

}


fun <T, R> mapToUiState(netResult: CUNetworkResult<T>, transform: (T) -> R): CUUiState<R> {
    return when (netResult) {
        is CUNetworkResult.Success -> CUUiState.Success(
            data = netResult.data?.let(transform)
        )

        is CUNetworkResult.BackendError -> CUUiState.Error(
            message = netResult.message
        )

        is CUNetworkResult.HttpError -> CUUiState.Error(
            message = netResult.message
        )

        is CUNetworkResult.NetworkError -> CUUiState.Error(
            message = netResult.exception.message.orEmpty()
        )

        is CUNetworkResult.SerializationError -> CUUiState.Error(
            message = netResult.rawBody.orEmpty()
        )
    }
}