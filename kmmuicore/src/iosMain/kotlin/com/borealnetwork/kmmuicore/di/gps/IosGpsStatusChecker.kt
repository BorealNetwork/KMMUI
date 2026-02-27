package com.borealnetwork.kmmuicore.di.gps

import com.borealnetwork.kmmuicore.domain.gps.GpsStatusChecker
import platform.CoreLocation.CLLocationManager

class IosGpsStatusChecker : GpsStatusChecker {

    override fun isGpsEnabled(): Boolean {
        return CLLocationManager.locationServicesEnabled()
    }
}