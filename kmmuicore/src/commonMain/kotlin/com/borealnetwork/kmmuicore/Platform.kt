package com.borealnetwork.kmmuicore

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier


expect inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
): KoinDefinition<T>



expect fun getFixedImageBytes(path: String, maxWidth: Int = 2000): ByteArray
expect class GeocodingService {
    suspend fun getAddress(lat: Double, lon: Double): String?
}



expect fun closeApplication()

@Composable
expect fun BackPressHandler(
    isEnabled: Boolean = true,
    onBackPressed: () -> Unit
)