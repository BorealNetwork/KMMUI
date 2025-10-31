package com.borealnetwork.kmmui.di.core


import com.borealnetwork.kmmui.data.datasource.core.CUBaseRemoteDataSourceImpl
import org.koin.dsl.module

val coreModule = module {
    single {
        CUBaseRemoteDataSourceImpl(get(), get())
    }
}

