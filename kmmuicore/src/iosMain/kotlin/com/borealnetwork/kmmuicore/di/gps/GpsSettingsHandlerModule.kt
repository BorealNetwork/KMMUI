package com.borealnetwork.kmmuicore.di.gps

import com.borealnetwork.kmmuicore.domain.gps.GpsActivatorHandler
import com.borealnetwork.kmmuicore.domain.gps.IosGpsActivatorHandler
import org.koin.dsl.module

val iosGpsSettingsHandlerModule = module {
    single<GpsActivatorHandler> { IosGpsActivatorHandler() }
}