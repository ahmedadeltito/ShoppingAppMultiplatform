package com.techietaka.shoppingapp.productlist.presentation.udf

import com.techietaka.shoppingapp.productlist.presentation.entity.ProductEntity

sealed interface ProductListUiContract

data class ProductListState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val productList: List<ProductEntity> = emptyList(),
) : ProductListUiContract

sealed interface ProductListEvent : ProductListUiContract {
    data object GetProductList : ProductListEvent
}

sealed interface ProductListSideEffect : ProductListUiContract {
    data class NavigateToProductDetails(val productId: Int) : ProductListSideEffect
    data class ShowSnackBar(val message: String) : ProductListSideEffect
}