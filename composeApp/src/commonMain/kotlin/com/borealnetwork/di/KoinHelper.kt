package com.borealnetwork.di

import com.borealnetwork.kmmuicore.di.initKoin
import org.koin.core.KoinApplication

fun koinInjection(appDeclaration: KoinApplication.() -> Unit = {}) {
    initKoin().also {
        appDeclaration(it)
        //if your had another koin Dependencies add modules here
    }
}