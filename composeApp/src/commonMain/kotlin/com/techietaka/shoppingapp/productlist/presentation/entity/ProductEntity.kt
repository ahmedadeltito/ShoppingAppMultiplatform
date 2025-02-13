package com.techietaka.shoppingapp.productlist.presentation.entity

import androidx.compose.runtime.Immutable

@Immutable
data class ProductEntity(
    val id: Int,
    val title: String,
    val price: Double,
    val category: String,
    val image: String,
    val rating: RatingEntity,
)

@Immutable
data class RatingEntity(
    val rate: Double,
    val count: Int,
)