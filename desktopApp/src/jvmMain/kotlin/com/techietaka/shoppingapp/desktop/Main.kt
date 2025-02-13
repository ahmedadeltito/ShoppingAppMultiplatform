package com.techietaka.shoppingapp.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.techietaka.shoppingapp.di.initKoin
import com.techietaka.shoppingapp.productlist.presentation.ui.ProductListScreen

fun main() = application {
    initKoin(
        baseUrl = "https://fakestoreapi.com/",
        enableNetworkLogs = true
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "ShoppingAppMultiplatform",
    ) {
        ProductListScreen { }
    }
}