package com.borealnetwork.kmmuicore.di.gps

import com.borealnetwork.kmmuicore.domain.gps.GpsStatusListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.darwin.NSObject

 class IosGpsStatusListener : GpsStatusListener {

     private val locationManager = CLLocationManager()

     private val _isGpsEnabled = MutableStateFlow(CLLocationManager.locationServicesEnabled())
     override val isGpsEnabled: StateFlow<Boolean> = _isGpsEnabled.asStateFlow()

     // 1. Creamos una clase interna que herede de NSObject de forma aislada
     private inner class GpsDelegate : NSObject(), CLLocationManagerDelegateProtocol {
         override fun locationManagerDidChangeAuthorization(manager: CLLocationManager) {
             _isGpsEnabled.value = CLLocationManager.locationServicesEnabled()
         }
     }

     // 2. Guardamos una referencia fuerte para que el recolector de basura no lo borre
     private val delegate = GpsDelegate()

     init {
         // 3. Le pasamos nuestro delegado nativo a iOS
         locationManager.delegate = delegate
     }
}