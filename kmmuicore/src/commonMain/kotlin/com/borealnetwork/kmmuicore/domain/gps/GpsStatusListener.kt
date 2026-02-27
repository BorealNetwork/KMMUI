package com.borealnetwork.kmmuicore.domain.gps

import kotlinx.coroutines.flow.StateFlow

interface GpsStatusListener {
    val isGpsEnabled: StateFlow<Boolean>
}