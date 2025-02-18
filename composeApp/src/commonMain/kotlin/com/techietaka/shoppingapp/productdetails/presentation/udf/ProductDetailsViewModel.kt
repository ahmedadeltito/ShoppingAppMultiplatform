package com.techietaka.shoppingapp.productdetails.presentation.udf

import androidx.lifecycle.viewModelScope
import com.techietaka.shoppingapp.core.base.BaseViewModel
import com.techietaka.shoppingapp.model.mapper.toUiModel
import com.techietaka.shoppingapp.productdetails.data.remote.ProductDetailsApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val productDetailsApiService: ProductDetailsApiService,
) : BaseViewModel<ProductDetailsUiState, ProductDetailsEvent, ProductDetailsSideEffect>() {

    override fun initState(): ProductDetailsUiState = ProductDetailsUiState()

    override fun onLoading(isLoading: Boolean) {
        updateState { it.copy(isLoading = isLoading) }
    }

    override fun sendSideEffect(sideEffect: ProductDetailsSideEffect) {
        viewModelScope.launch(context = coroutineDispatcher) {
            sideEffectFlow.send(element = sideEffect)
        }
    }

    override fun onEvent(event: ProductDetailsEvent) {
        when (event) {
            is ProductDetailsEvent.GetProduct -> getProduct(productId = event.productId)
        }
    }

    private fun getProduct(productId: Int) {
        viewModelScope.launch(context = coroutineDispatcher) {
            productDetailsApiService.getProduct(productId = productId)
                .onStart { onLoading(isLoading = true) }
                .onEach { product ->
                    updateState {
                        it.copy(
                            isLoading = false,
                            product = product.toUiModel()
                        )
                    }
                }
                .catch { throwable ->
                    val errorMessage = throwable.message ?: "Something went wrong"
                    updateState { it.copy(isLoading = false, errorMessage = errorMessage) }
                }
                .collect()
        }
    }
}