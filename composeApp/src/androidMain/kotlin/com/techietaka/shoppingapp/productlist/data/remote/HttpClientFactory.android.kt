package com.techietaka.shoppingapp.productlist.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android

actual fun createPlatformHttpClient(): HttpClient = HttpClient(Android)