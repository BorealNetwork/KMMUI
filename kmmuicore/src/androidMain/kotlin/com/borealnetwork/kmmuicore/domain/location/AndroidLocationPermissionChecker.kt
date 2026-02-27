package com.borealnetwork.kmmuicore.domain.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class AndroidLocationPermissionChecker(
    private val context: Context
) : LocationPermissionChecker {

    override fun hasLocationPermission(): Boolean {
        // Verificamos si tenemos el permiso de ubicación precisa
        val fineLocationGranted = ContextCompat.checkSelfPermission(
            context, 
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        
        // Verificamos si tenemos el permiso de ubicación aproximada
        val coarseLocationGranted = ContextCompat.checkSelfPermission(
            context, 
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        // Retorna true si tenemos al menos uno de los dos
        return fineLocationGranted || coarseLocationGranted
    }
}