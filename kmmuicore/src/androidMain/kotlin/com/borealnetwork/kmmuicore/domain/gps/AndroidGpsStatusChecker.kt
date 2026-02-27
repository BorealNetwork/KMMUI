package com.borealnetwork.kmmuicore.domain.gps

import android.content.Context
import android.location.LocationManager
import androidx.core.location.LocationManagerCompat

class AndroidGpsStatusChecker(
    private val context: Context
) : GpsStatusChecker {

    override fun isGpsEnabled(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
        
        return if (locationManager != null) {
            LocationManagerCompat.isLocationEnabled(locationManager)
        } else {
            false
        }
    }
}