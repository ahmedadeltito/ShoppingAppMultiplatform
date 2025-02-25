package com.techietaka.shoppingapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.techietaka.shoppingapp.di.initKoin
import com.techietaka.shoppingapp.navigation.AppNavigation
import com.techietaka.shoppingapp.theme.ShoppingAppTheme
import platform.UIKit.UIViewController

@Suppress("FunctionName", "unused")
fun MainViewController(): UIViewController {

    initKoin(baseUrl = "https://fakestoreapi.com/", enableNetworkLogs = true)

    return ComposeUIViewController {
        ShoppingAppTheme {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.fillMaxSize()
            ) {
                AppNavigation()
            }
        }
    }
}