package com.borealnetwork.kmmuicore.domain.location// iosMain/src/.../IosLocationPermissionListener.kt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedAlways
import platform.CoreLocation.kCLAuthorizationStatusAuthorizedWhenInUse
import platform.darwin.NSObject

class IosLocationPermissionListener :LocationPermissionListener {

    private val locationManager = CLLocationManager()

    private val _hasPermission = MutableStateFlow(checkPermission())
    override val hasLocationPermission: StateFlow<Boolean> = _hasPermission.asStateFlow()

    // 1. Aislamos el NSObject en su propia clase interna
    private inner class PermissionDelegate : NSObject(), CLLocationManagerDelegateProtocol {
        override fun locationManagerDidChangeAuthorization(manager: CLLocationManager) {
            _hasPermission.value = checkPermission()
        }
    }

    // 2. Referencia fuerte al delegado
    private val delegate = PermissionDelegate()

    init {
        locationManager.delegate = delegate
    }

    override fun updatePermissionStatus() {
        _hasPermission.value = checkPermission()
    }

    private fun checkPermission(): Boolean {
        val status = CLLocationManager.authorizationStatus()
        return status == kCLAuthorizationStatusAuthorizedWhenInUse ||
                status == kCLAuthorizationStatusAuthorizedAlways
    }
}