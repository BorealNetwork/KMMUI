package com.borealnetwork.kmmuicore.network.core

sealed class CUUiState<out T> {

    data class Success<T>(val data: T?) : CUUiState<T>()

    data class Error<T>(val message: String, val error: String? = null) : CUUiState<T>()

    object Loading : CUUiState<Nothing>()
    object None : CUUiState<Nothing>()

}