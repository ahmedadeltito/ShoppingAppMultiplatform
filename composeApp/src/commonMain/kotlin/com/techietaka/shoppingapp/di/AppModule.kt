package com.techietaka.shoppingapp.di

import com.techietaka.shoppingapp.productlist.data.di.remoteModule
import com.techietaka.shoppingapp.productlist.presentation.di.dispatcherModule
import com.techietaka.shoppingapp.productlist.presentation.di.viewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    enableNetworkLogs: Boolean,
    baseUrl: String,
    appDeclaration: KoinAppDeclaration = {}
) =
    startKoin {
        appDeclaration()
        modules(
            remoteModule(enableNetworkLogs, baseUrl),
            dispatcherModule,
            viewModelModule
        )
    }