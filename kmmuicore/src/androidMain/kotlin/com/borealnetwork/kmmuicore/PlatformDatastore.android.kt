package com.borealnetwork.kmmuicore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.borealnetwork.kmmuicore.data.datastore.createDataStore
import com.borealnetwork.kmmuicore.domain.datastore.DataStorePath
import org.koin.dsl.module


fun provideDataStore(context: Context, fileName: String): DataStore<Preferences> = createDataStore {
    context.filesDir.resolve(fileName).absolutePath
}


actual val dataStoreModule = module {
    single<DataStore<Preferences>> {
        val dataStorePath = get<DataStorePath>().path
        provideDataStore(context = get(), dataStorePath)
    }
}