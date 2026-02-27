package com.bimbonet.prospeccion.di.location

import com.borealnetwork.kmmuicore.domain.location.LocationPermissionChecker
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse

class IosLocationPermissionChecker : LocationPermissionChecker {

    override fun hasLocationPermission(): Boolean {
        // Obtenemos el estado actual de los permisos
        val status = CLLocationManager.authorizationStatus()

        // Retorna true si el usuario nos dio permiso mientras usa la app o siempre
        return status == kCLAuthorizationStatusAuthorizedWhenInUse ||
                status == kCLAuthorizationStatusAuthorizedAlways
    }
}