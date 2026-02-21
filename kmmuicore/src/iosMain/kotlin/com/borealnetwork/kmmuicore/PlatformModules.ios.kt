package com.borealnetwork.kmmuicore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.borealnetwork.kmmuicore.domain.datastore.DataStorePath
import org.koin.dsl.module

actual val dataStoreModule = module {
    single<DataStore<Preferences>> {
        val dataStorePath = get<DataStorePath>().path
        provideDataStore(dataStorePath) // La función que definimos antes para iOS
    }
}


val iosModule = module {
    single { GeocodingService() }
}
