package com.borealnetwork.kmmuicore.di

import com.borealnetwork.kmmuicore.di.core.coreModule
import com.borealnetwork.kmmuicore.di.network.networkModule
import org.koin.core.context.startKoin

//@Composable
//inline fun <reified T: ViewModel> koinViewModel(): T {
//    val scope = currentKoinScope()
//    return viewModel {
//        scope.get<T>()
//    }
//}

fun initKoin() = startKoin {
    modules(
        networkModule,
        coreModule
    )
}