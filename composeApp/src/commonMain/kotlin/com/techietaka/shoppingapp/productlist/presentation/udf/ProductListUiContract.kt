package com.techietaka.shoppingapp.productlist.presentation.udf

import com.techietaka.shoppingapp.model.ui.ProductUiModel

sealed interface ProductListUiContract

data class ProductListState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val productList: List<ProductUiModel> = emptyList(),
) : ProductListUiContract

sealed interface ProductListEvent : ProductListUiContract {
    data object GetProductList : ProductListEvent
}

sealed interface ProductListSideEffect : ProductListUiContract {
    data class NavigateToProductDetails(val productId: Int) : ProductListSideEffect
    data class ShowSnackBar(val message: String) : ProductListSideEffect
}