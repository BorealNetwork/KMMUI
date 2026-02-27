package com.borealnetwork.kmmuicore.di.gps

import com.borealnetwork.kmmuicore.domain.gps.GpsActivatorHandler
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

// Este objeto actúa como un puente entre Koin y tu UI en iOS
object IosDIHelper : KoinComponent {
    // Usas 'by inject()' para obtener las dependencias perezosamente (lazy)
    val gpsActivator: GpsActivatorHandler by inject()
}