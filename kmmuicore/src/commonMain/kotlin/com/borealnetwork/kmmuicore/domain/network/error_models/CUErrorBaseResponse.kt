package com.borealnetwork.kmmuicore.domain.network.error_models

import kotlinx.serialization.Serializable

@Serializable
data class CUErrorBaseResponse(
    val code: String,
    val message: String
)