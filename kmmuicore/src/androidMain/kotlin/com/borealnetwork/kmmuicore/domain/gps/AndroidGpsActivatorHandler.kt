package com.borealnetwork.kmmuicore.domain.gps

import android.content.Context
import android.content.Intent
import android.provider.Settings

class AndroidGpsActivatorHandler(private val context: Context) : GpsActivatorHandler {
    override fun openGpsSettings() {
        // Esto abre específicamente la pantalla del sistema para prender el GPS
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

}