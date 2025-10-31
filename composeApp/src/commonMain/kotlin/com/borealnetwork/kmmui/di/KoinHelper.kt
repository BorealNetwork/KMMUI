package com.borealnetwork.kmmui.di

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
        coreModule,
        loginModule,
        loginViewModelModule
    )
}