package com.borealnetwork.kmmui.network


data class CUApiResponse<T>(
    val response: T? = null,
    val failure: String? = null,
    var status: CUStateApi = CUStateApi.None
)

// Extension function to check if the status is success
fun <T> CUApiResponse<T>.success(result: (CUApiResponse<T>) -> Unit) {
    if (status == CUStateApi.Success) {
        result(this)
    }
}

// Extension function to create a loading state
fun <T> loading(savedBundle: T? = null) = CUApiResponse<T>(
    status = CUStateApi.Loading,
    response = savedBundle
)

// Extension function to check if the status is error
fun <T> CUApiResponse<T>.error(result: (CUApiResponse<T>) -> Unit) {
    if (status == CUStateApi.Error) {
        result(this)
    }
}