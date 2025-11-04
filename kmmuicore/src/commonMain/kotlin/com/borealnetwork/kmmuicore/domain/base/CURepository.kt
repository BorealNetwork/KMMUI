package com.borealnetwork.kmmuicore.domain.base

import com.borealnetwork.kmmuicore.network.core.CUUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class CURepository {
    fun <T> toResultFlow(call: suspend () -> T?): Flow<CUUiState<T?>> {
        return flow<CUUiState<T?>> {
            emit(CUUiState.Loading)
            val c = call.invoke()
            c.let { response ->
                try {
                    emit(CUUiState.Success(response))
                } catch (e: Exception) {
                    emit(CUUiState.Error(e.toString()))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}

