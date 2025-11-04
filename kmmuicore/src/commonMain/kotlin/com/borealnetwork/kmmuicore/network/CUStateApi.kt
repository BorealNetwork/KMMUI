package com.borealnetwork.kmmuicore.network

import com.borealnetwork.kmmuicore.domain.error.CUErrorData
import com.borealnetwork.kmmuicore.network.CUStateApi.Error.error


sealed class CUStateApi {
    data object Loading : CUStateApi()
    data object Success : CUStateApi()
    data object Error : CUStateApi() {
        var error: CUErrorData? = null
        fun error(errorData: CUErrorData): Error {
            error = errorData
            return this
        }
    }

    data object None : CUStateApi()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[]"
            is Loading -> "Loading[]"
            is None -> ""
            is Error -> "${error?.code.toString()} - ${error?.message.toString()}"
        }
    }
}