package com.techietaka.shoppingapp.di

import org.koin.core.context.startKoin

fun initKoin(
    enableNetworkLogs: Boolean,
    baseUrl: String,
) = startKoin {
    modules(
        remoteModule(enableNetworkLogs, baseUrl),
        dispatcherModule,
        viewModelModule
    )
}