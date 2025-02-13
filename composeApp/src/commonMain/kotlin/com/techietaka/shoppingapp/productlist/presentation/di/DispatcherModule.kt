package com.techietaka.shoppingapp.productlist.presentation.di

import com.techietaka.shoppingapp.core.dispatcher.shoppingAppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.dsl.module

val dispatcherModule = module {
    single<CoroutineDispatcher> { shoppingAppDispatchers.io }
}