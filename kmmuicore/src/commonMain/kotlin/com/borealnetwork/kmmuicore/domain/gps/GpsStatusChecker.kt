package com.borealnetwork.kmmuicore.domain.gps

interface GpsStatusChecker {
    fun isGpsEnabled(): Boolean
}