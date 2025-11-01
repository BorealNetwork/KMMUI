package com.borealnetwork.kmmui

import androidx.compose.ui.window.ComposeUIViewController
import androidx.navigation.compose.rememberNavController

fun MainViewController() = ComposeUIViewController { App(navController = rememberNavController()) }
