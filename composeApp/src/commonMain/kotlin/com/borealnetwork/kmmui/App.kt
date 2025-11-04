package com.borealnetwork.kmmui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.borealnetwork.kmmuicore.ui.components.SemiBoldText
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(navController: NavController = rememberNavController()) {
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SemiBoldText(
                text = "SemiboldExample"
            )
        }
    }
}