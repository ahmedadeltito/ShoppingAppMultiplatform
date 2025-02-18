package com.techietaka.shoppingapp.model.entity

data class ProductEntity(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingEntity,
)

data class RatingEntity(
    val rate: Double,
    val count: Int,
)