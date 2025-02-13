package com.techietaka.shoppingapp.productlist.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual fun createPlatformHttpClient(): HttpClient = HttpClient(Darwin)