package com.borealnetwork.kmmuicore.domain.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borealnetwork.kmmuicore.network.core.CUUiState
import com.borealnetwork.kmmuicore.utils.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class CUViewModel : ViewModel() {

    protected fun <T> fetchData(
        uiStateFlow: MutableStateFlow<CUUiState<T?>>,
        interceptor: suspend (CUUiState<T?>) -> Unit = {},
        apiCall: suspend () -> Flow<CUUiState<T?>>
    ) = viewModelScope.launch {
        uiStateFlow.value = CUUiState.Loading
        try {
            apiCall().collect {
                interceptor.invoke(it)
                uiStateFlow.value = it
            }
        } catch (e: Exception) {
            println(e.stackTraceToString())
            uiStateFlow.value = CUUiState.Error(e.message ?: "")
        }

    }


    protected fun executeFlow(block: suspend () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                block.invoke()
            } catch (e: Exception) {
                e.stackTraceToString().log("ERROR_VMODEL")
            }
        }


}


