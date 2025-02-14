package com.techietaka.shoppingapp.di

import com.techietaka.shoppingapp.productdetails.presentation.udf.ProductDetailsViewModel
import com.techietaka.shoppingapp.productlist.presentation.udf.ProductListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ProductListViewModel(coroutineDispatcher = get(), productLitApiService = get())
    }
    viewModel {
        ProductDetailsViewModel(coroutineDispatcher = get(), productDetailsApiService = get())
    }
}