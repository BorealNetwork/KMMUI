package com.borealnetwork.kmmuicore.di

import com.borealnetwork.kmmuicore.di.core.coreModule
import com.borealnetwork.kmmuicore.di.network.networkModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(
        networkModule,
        coreModule
    )
}