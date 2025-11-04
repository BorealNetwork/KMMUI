package com.borealnetwork.kmmui

import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.borealnetwork.kmmuicore.di.initKoin
import org.koin.android.ext.koin.androidContext

class AndroidApp : Application() {
    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        initKoin().apply {
            androidContext(this@AndroidApp)
        }
        INSTANCE = this
    }
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
            enableEdgeToEdge()
        }
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            App(navController = navController)
        }
    }
}