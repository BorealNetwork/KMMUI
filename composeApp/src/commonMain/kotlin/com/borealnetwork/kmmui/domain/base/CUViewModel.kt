package com.borealnetwork.kmmui.domain.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borealnetwork.kmmui.domain.error.CUErrorData
import com.borealnetwork.kmmui.network.core.CUNetworkResult
import com.borealnetwork.kmmui.network.core.CUUiState
import com.borealnetwork.kmmui.network.core.mapToUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class CUViewModel : ViewModel() {

    protected fun <T> fetchData(
        uiStateFlow: MutableStateFlow<CUUiState<T?>>,
        apiCall: suspend () -> Flow<CUUiState<T?>>
    ) = viewModelScope.launch {
//        if (hasInternetConnection(context)) {
        uiStateFlow.value = CUUiState.Loading
        try {
            apiCall().collect {
                uiStateFlow.value = it
            }
        } catch (e: Exception) {
            println(e.stackTraceToString())
            uiStateFlow.value = CUUiState.Error(e.message ?: "")
        }

    }

    /**
     * Lanza una corrutina que ejecuta un bloque de código y actualiza un MutableStateFlow con el estado de la operación.
     * Verifica la conexión a internet antes de ejecutar el bloque.
     *
     * @param stateFlow MutableStateFlow<CUUiState<R>> flujo de estado a actualizar
     * @param showLoading Boolean indica si se debe mostrar el estado de carga al iniciar (por defecto es true)
     * @param block función suspendida que realiza la operación y devuelve un CUNetworkResult<T>
     * @param transform función que transforma los datos de tipo T a tipo R en caso de éxito
     */
    protected fun <T, R> launchWithState(
        stateFlow: MutableStateFlow<CUUiState<R>>,
        showLoading: Boolean = true,
        block: suspend () -> CUNetworkResult<T>,
        transform: (T) -> R
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            // Verificar si hay conexión a internet antes de proceder

            if (showLoading) {
                stateFlow.emit(CUUiState.Loading)
            } else {
                // para limpiar cualquier estado previo (Error, Success, o incluso Loading(false))
                // Si el estado anterior ya era Idle, esto no hace nada.
                // Si era Success/Error de una operación previa, esto lo limpia.
                if (stateFlow.value !is CUUiState.None) {
                    stateFlow.emit(CUUiState.None)
                }
            }

            try {
                val result = block()
                val uiState = mapToUiState(result, transform)
                stateFlow.emit(uiState)
            } catch (throwable: Throwable) {
                stateFlow.emit(CUUiState.Error(CUErrorData.ERROR_DEFAULT.message))
            }
        }
    }


}


