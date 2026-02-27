package com.borealnetwork.kmmuicore.di.gps

import com.borealnetwork.kmmuicore.domain.gps.AndroidGpsActivatorHandler
import com.borealnetwork.kmmuicore.domain.gps.GpsActivatorHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidGpsSettingsHandler = module {
    single<GpsActivatorHandler> { AndroidGpsActivatorHandler(androidContext()) }
}