package com.borealnetwork.kmmuicore

import org.koin.core.module.Module

expect val locationPermissionModule: Module
expect val gpsStatusCheckerModule: Module
expect val gpsStatusListenerModule: Module
expect val locationPermissionListenerModule: Module

expect class GeocodingService {
    suspend fun getAddress(lat: Double, lon: Double): String?
}
