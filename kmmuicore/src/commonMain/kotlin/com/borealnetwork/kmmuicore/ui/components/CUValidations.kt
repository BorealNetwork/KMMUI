package com.borealnetwork.kmmuicore.ui.components

import androidx.compose.runtime.Composable
import com.borealnetwork.kmmuicore.network.core.CUUiState

@Composable
fun ValidationQuery(status: CUUiState<*>, onError: (String) -> Unit = {},onNextView: () -> Unit = {}) {
    when (status) {
        is CUUiState.Error<*> -> {
            status.message?.let {
                println(it)
                onError(it)
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