package com.borealnetwork.kmmuicore.domain.network.error_models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorBaseResponseV1(
    val respuesta: String,
    val codigo: String,
    val descripcion: String
)