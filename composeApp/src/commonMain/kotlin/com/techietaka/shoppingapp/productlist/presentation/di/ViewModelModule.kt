package com.techietaka.shoppingapp.productlist.presentation.di

import com.techietaka.shoppingapp.productlist.presentation.udf.ProductListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProductListViewModel(coroutineDispatcher = get(), productLitApiService = get()) }
}