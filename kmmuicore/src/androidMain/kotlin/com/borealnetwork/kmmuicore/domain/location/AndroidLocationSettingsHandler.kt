package com.borealnetwork.kmmuicore.domain.location

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

class AndroidLocationSettingsHandler(private val context: Context) : LocationSettingsHandler {
    override fun openLocationSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // Necesario si usas el contexto de aplicación
        }
        context.startActivity(intent)
    }
}