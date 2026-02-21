package com.borealnetwork.kmmuicore.di.connectivity

import com.plusmobileapps.konnectivity.Konnectivity
import org.koin.dsl.module

val konnectivity = module {
    factory { Konnectivity() }
}