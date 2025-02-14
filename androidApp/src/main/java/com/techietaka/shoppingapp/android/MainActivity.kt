package com.techietaka.shoppingapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.techietaka.shoppingapp.di.initKoin
import com.techietaka.shoppingapp.navigation.AppNavigation
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKoin(enableNetworkLogs = BuildConfig.DEBUG, baseUrl = BuildConfig.BASE_URL)

        setContent {
            AppNavigation()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}