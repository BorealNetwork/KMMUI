package com.borealnetwork.kmmuicore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.borealnetwork.kmmuicore.data.datastore.createDataStore
import com.borealnetwork.kmmuicore.domain.datastore.DataStorePath
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
fun provideDataStore(fileName: String) = createDataStore {
    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )
    requireNotNull(documentDirectory).path + "/$fileName"
}

actual val dataStoreModule = module {
    single<DataStore<Preferences>> {
        val dataStorePath = get<DataStorePath>().path
        provideDataStore(dataStorePath) // La función que definimos antes para iOS
    }
}
