package com.borealnetwork.kmmuicore

import com.bimbonet.prospeccion.di.location.IosLocationPermissionChecker
import com.borealnetwork.kmmuicore.di.gps.IosGpsStatusChecker
import com.borealnetwork.kmmuicore.di.gps.IosGpsStatusListener
import com.borealnetwork.kmmuicore.domain.gps.GpsStatusChecker
import com.borealnetwork.kmmuicore.domain.gps.GpsStatusListener
import com.borealnetwork.kmmuicore.domain.location.IosLocationPermissionListener
import com.borealnetwork.kmmuicore.domain.location.LocationPermissionChecker
import com.borealnetwork.kmmuicore.domain.location.LocationPermissionListener
import kotlinx.coroutines.suspendCancellableCoroutine
import org.koin.dsl.module
import platform.CoreLocation.CLGeocoder
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLPlacemark
import kotlin.coroutines.resume


actual val locationPermissionModule = module {
    single<LocationPermissionChecker> {
        IosLocationPermissionChecker()
    }
}

actual val gpsStatusCheckerModule = module {
    single<GpsStatusChecker> { IosGpsStatusChecker() }
}


actual val gpsStatusListenerModule = module {
    single<GpsStatusListener> { IosGpsStatusListener() }
}

actual val locationPermissionListenerModule = module {
    // NUEVO: Agregamos el listener de los permisos
    single<LocationPermissionListener> { IosLocationPermissionListener() }
}

val iosGeocodingServiceModule = module {
    single { GeocodingService() }
}
actual class GeocodingService {
    actual suspend fun getAddress(lat: Double, lon: Double): String? =
        suspendCancellableCoroutine { continuation ->
            val geocoder = CLGeocoder()
            val location = CLLocation(latitude = lat, longitude = lon)

            // Requiere manejo de callbacks (suspendCoroutine recomendado aquí)
            // Simplificado:
            geocoder.reverseGeocodeLocation(location) { placemarks, error ->
                if (error != null) {
                    // Si hay error, resumimos con null o podrías lanzar una excepción
                    continuation.resume(null)
                    return@reverseGeocodeLocation
                }

                val placemark = placemarks?.firstOrNull() as? CLPlacemark
                if (placemark != null) {
                    // Extraemos los componentes de la dirección
                    val street = placemark.thoroughfare ?: ""       // Calle
                    val number = placemark.subThoroughfare ?: ""    // Número
                    val city = placemark.locality ?: ""             // Ciudad
                    val state = placemark.administrativeArea ?: ""   // Estado/Provincia

                    val fullAddress = listOfNotNull(
                        if (street.isNotEmpty()) "$street $number".trim() else null,
                        city.ifEmpty { null },
                        state.ifEmpty { null }
                    ).joinToString(", ")

                    continuation.resume(fullAddress)
                } else {
                    continuation.resume(null)
                }
            }
        }
}