package com.borealnetwork.kmmuicore.domain.location

import kotlinx.coroutines.flow.StateFlow

interface LocationPermissionListener {
    val hasLocationPermission: StateFlow<Boolean>
    fun updatePermissionStatus()
}