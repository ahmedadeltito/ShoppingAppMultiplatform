package com.techietaka.shoppingapp.productlist.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java

actual fun createPlatformHttpClient(): HttpClient = HttpClient(Java)