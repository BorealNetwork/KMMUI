package com.borealnetwork.kmmuicore.ui.components

import androidx.compose.runtime.Composable
import com.borealnetwork.kmmuicore.network.core.CUUiState

@Composable
fun ValidationQuery(
    status: CUUiState<*>,
    onError: (CUUiState.Error<*>) -> Unit = {},
    onLoadingContent: @Composable (() -> Unit) = { ProgressLoader(true) },
    onNextView: () -> Unit = {}
) {
    when (status) {
        is CUUiState.Error<*> -> {
            status.message?.let {
                println(it)
                onError(status)
            }
        }

        is CUUiState.Loading -> {
            onLoadingContent()
        }


        is CUUiState.Success<*> -> {
            //NEXT VIEW
            onNextView()
        }

        else -> {}
    }
}