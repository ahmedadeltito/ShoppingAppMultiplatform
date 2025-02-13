package com.techietaka.shoppingapp.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface ShoppingAppDispatcher {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

expect val shoppingAppDispatchers: ShoppingAppDispatcher