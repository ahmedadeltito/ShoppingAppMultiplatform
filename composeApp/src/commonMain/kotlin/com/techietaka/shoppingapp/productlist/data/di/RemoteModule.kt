package com.techietaka.shoppingapp.productlist.data.di

import com.techietaka.shoppingapp.productlist.data.remote.ProductListApiService
import com.techietaka.shoppingapp.productlist.data.remote.createPlatformHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

val remoteModule: (
    enableLogging: Boolean,
    baseUrl: String
) -> Module
    get() = { enableLogging, baseUrl ->
        module {
            single { provideKtorClient(enableLogging = enableLogging, baseUrl = baseUrl) }
            single<ProductListApiService> { ProductListApiService(get()) }
        }
    }

fun provideKtorClient(
    enableLogging: Boolean,
    baseUrl: String
): HttpClient = createPlatformHttpClient().config {
    install(ContentNegotiation) {
        json(
            Json {
                coerceInputValues = true
                ignoreUnknownKeys = true
            }
        )
    }
    defaultRequest {
        url(urlString = baseUrl)
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 10_000
        connectTimeoutMillis = 10_000
        socketTimeoutMillis = 10_000
    }
    install(Logging) {
        if (!enableLogging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
}