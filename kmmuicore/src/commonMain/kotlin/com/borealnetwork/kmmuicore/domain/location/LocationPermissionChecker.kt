package com.borealnetwork.kmmuicore.domain.location

interface LocationPermissionChecker {
    fun hasLocationPermission(): Boolean
}