package com.bimbonet.prospeccion.di.location

import com.borealnetwork.kmmuicore.domain.location.IosLocationSettingsHandler
import com.borealnetwork.kmmuicore.domain.location.LocationSettingsHandler
import org.koin.dsl.module

val locationSettingsHandlerModule = module {
    single<LocationSettingsHandler> { IosLocationSettingsHandler() }
}