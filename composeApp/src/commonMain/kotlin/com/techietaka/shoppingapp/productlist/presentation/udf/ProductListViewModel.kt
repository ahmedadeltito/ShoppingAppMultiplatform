package com.techietaka.shoppingapp.productlist.presentation.udf

import androidx.lifecycle.viewModelScope
import com.techietaka.shoppingapp.core.base.BaseViewModel
import com.techietaka.shoppingapp.productlist.data.remote.ProductListApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val productLitApiService: ProductListApiService
) : BaseViewModel<ProductListState, ProductListEvent, ProductListSideEffect>() {

    override fun initState(): ProductListState = ProductListState()

    override fun onLoading(isLoading: Boolean) {
        updateState { it.copy(isLoading = isLoading) }
    }

    override fun sendSideEffect(sideEffect: ProductListSideEffect) {
        viewModelScope.launch(coroutineDispatcher) {
            sideEffectFlow.send(sideEffect)
        }
    }

    override fun onEvent(event: ProductListEvent) {
        when (event) {
            ProductListEvent.GetProductList -> getProductList()
        }
    }

    private fun getProductList() {
        viewModelScope.launch(coroutineDispatcher) {
            productLitApiService.getProductList()
                .onStart { onLoading(isLoading = true) }
                .onEach { productList ->
                    updateState { it.copy(isLoading = false, productList = productList) }
                }
                .catch { throwable ->
                    val errorMessage = throwable.message ?: "Something Went Wrong!"
                    updateState { it.copy(isLoading = false, errorMessage = errorMessage) }
                }
                .collect()
        }
    }
}