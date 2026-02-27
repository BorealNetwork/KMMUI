package com.borealnetwork.kmmuicore

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import com.borealnetwork.kmmuicore.domain.gps.AndroidGpsStatusChecker
import com.borealnetwork.kmmuicore.domain.gps.AndroidGpsStatusListener
import com.borealnetwork.kmmuicore.domain.gps.GpsStatusChecker
import com.borealnetwork.kmmuicore.domain.gps.GpsStatusListener
import com.borealnetwork.kmmuicore.domain.location.AndroidLocationPermissionChecker
import com.borealnetwork.kmmuicore.domain.location.AndroidLocationPermissionListener
import com.borealnetwork.kmmuicore.domain.location.LocationPermissionChecker
import com.borealnetwork.kmmuicore.domain.location.LocationPermissionListener
import kotlinx.coroutines.suspendCancellableCoroutine
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.Locale
import kotlin.coroutines.resume


actual val locationPermissionModule = module {
    // Le pasamos el androidContext() que Koin provee automáticamente
    single<LocationPermissionChecker> {
        AndroidLocationPermissionChecker(androidContext())
    }
}

actual val gpsStatusCheckerModule = module {

    single<GpsStatusChecker> {
        AndroidGpsStatusChecker(androidContext())
    }
}

actual val gpsStatusListenerModule = module {
    single<GpsStatusListener> { AndroidGpsStatusListener(androidContext()) }
}

actual val locationPermissionListenerModule = module {
    single<LocationPermissionListener> { AndroidLocationPermissionListener(androidContext()) }
}


val androidGeocodingServiceModule = module {
    // Android necesita el contexto para el Geocoder
    single { GeocodingService(get()) }
}

actual class GeocodingService(private val context: Context) {
    actual suspend fun getAddress(lat: Double, lon: Double): String? =
        suspendCancellableCoroutine { continuation ->
            val geocoder = Geocoder(context, Locale.getDefault())

            // 1. Verificamos la versión de Android
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                // Versión Nueva (API 33+) - Usa Callback
                geocoder.getFromLocation(lat, lon, 1, object : Geocoder.GeocodeListener {
                    override fun onGeocode(addresses: MutableList<Address>) {
                        val address = addresses.firstOrNull()
                        continuation.resume(formatAddress(address))
                    }

                    override fun onError(errorMessage: String?) {
                        continuation.resume(null)
                    }
                })
            } else {
                // Versión Antigua (Legacy)
                try {
                    @Suppress("DEPRECATION")
                    val address = geocoder.getFromLocation(lat, lon, 1)?.firstOrNull()
                    continuation.resume(formatAddress(address))
                } catch (e: Exception) {
                    continuation.resume(null)
                }
            }
        }

    // Función auxiliar para no repetir lógica de formateo
    private fun formatAddress(address: Address?): String? {
        if (address == null) return null

        val street = address.thoroughfare ?: ""      // Calle
        val number = address.subThoroughfare ?: ""   // Número
        val city = address.locality ?: ""            // Ciudad
        val state = address.adminArea ?: ""          // Estado

        return listOfNotNull(
            if (street.isNotEmpty()) "$street $number".trim() else null,
            city.ifEmpty { null },
            state.ifEmpty { null }
        ).joinToString(", ")
    }
}