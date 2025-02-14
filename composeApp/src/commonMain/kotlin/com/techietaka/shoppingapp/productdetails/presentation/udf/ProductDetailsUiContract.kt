package com.techietaka.shoppingapp.productdetails.presentation.udf

import com.techietaka.shoppingapp.model.entity.ProductEntity
import com.techietaka.shoppingapp.model.entity.RatingEntity

sealed interface ProductDetailsUiContract

data class ProductDetailsUiState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val product: ProductEntity = ProductEntity(
        id = -1,
        title = "",
        price = 0.0,
        description = "",
        category = "",
        image = "",
        rating = RatingEntity(rate = 0.0, count = 0)
    )
) : ProductDetailsUiContract

sealed interface ProductDetailsEvent : ProductDetailsUiContract {
    data class GetProduct(val productId: Int) : ProductDetailsEvent
}

sealed interface ProductDetailsSideEffect : ProductDetailsUiContract {
    data object NavigateToBack : ProductDetailsSideEffect
    data class ShowSnackBar(val message: String) : ProductDetailsSideEffect
}
