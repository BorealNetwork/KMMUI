package com.borealnetwork.kmmuicore.ui.components

import androidx.compose.runtime.Composable
import com.borealnetwork.kmmuicore.network.core.CUUiState

@Composable
fun ValidationQuery(status: CUUiState<*>, onError: (CUUiState.Error<*>) -> Unit = {},onNextView: () -> Unit = {}) {
    when (status) {
        is CUUiState.Error<*> -> {
            status.message?.let {
                println(it)
                onError(status)
            }
        }

        is CUUiState.Loading -> {
            ProgressLoader(true)
        }


        is CUUiState.Success<*> -> {
            //NEXT VIEW
            onNextView()
        }

        else -> {}
    }
}