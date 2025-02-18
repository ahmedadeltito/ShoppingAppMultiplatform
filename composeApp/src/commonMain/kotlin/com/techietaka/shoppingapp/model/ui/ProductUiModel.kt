package com.techietaka.shoppingapp.model.ui

import androidx.compose.runtime.Immutable

@Immutable
data class ProductUiModel(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingUiModel,
)

@Immutable
data class RatingUiModel(
    val rate: Double,
    val count: Int,
)