package com.techietaka.shoppingapp.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.techietaka.shoppingapp.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ShoppingAppMultiplatform",
    ) {
        App()
    }
}