package com.borealnetwork.kmmuicore.domain.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AndroidLocationPermissionListener(
    private val context: Context
) : LocationPermissionListener {

    private val _hasPermission = MutableStateFlow(checkPermission())
    override val hasLocationPermission = _hasPermission.asStateFlow()

    override fun updatePermissionStatus() {
        _hasPermission.value = checkPermission()
    }

    private fun checkPermission(): Boolean {
        val fine = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        val coarse = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        return fine || coarse
    }
}