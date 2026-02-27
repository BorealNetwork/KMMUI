package com.borealnetwork.kmmuicore.domain.gps

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import androidx.core.location.LocationManagerCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AndroidGpsStatusListener(private val context: Context) : GpsStatusListener {

    // Inicializamos con el valor actual del GPS
    private val _isGpsEnabled = MutableStateFlow(checkGpsStatus())
    override val isGpsEnabled: StateFlow<Boolean> = _isGpsEnabled.asStateFlow()

    init {
        // Registramos un receptor para escuchar los cambios del GPS
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == LocationManager.PROVIDERS_CHANGED_ACTION) {
                    // Si el GPS cambió, actualizamos el Flow
                    _isGpsEnabled.value = checkGpsStatus()
                }
            }
        }
        // Le decimos a Android que nos avise de este evento específico
        context.registerReceiver(receiver, IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION))
    }

    private fun checkGpsStatus(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
        return locationManager?.let { LocationManagerCompat.isLocationEnabled(it) } ?: false
    }
}