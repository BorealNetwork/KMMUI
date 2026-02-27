package com.borealnetwork.kmmuicore.di.location

import com.borealnetwork.kmmuicore.domain.location.AndroidLocationSettingsHandler
import com.borealnetwork.kmmuicore.domain.location.LocationSettingsHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val locationSettingsHandler = module {
    single<LocationSettingsHandler> { AndroidLocationSettingsHandler(androidContext()) }
}