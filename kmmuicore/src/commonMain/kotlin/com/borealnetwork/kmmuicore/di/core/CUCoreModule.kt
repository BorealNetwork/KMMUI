package com.borealnetwork.kmmuicore.di.core


import com.borealnetwork.kmmuicore.data.datasource.core.CUBaseRemoteDataSourceImpl
import org.koin.dsl.module

val coreModule = module {
    single {
        CUBaseRemoteDataSourceImpl(get(), get())
    }
}

