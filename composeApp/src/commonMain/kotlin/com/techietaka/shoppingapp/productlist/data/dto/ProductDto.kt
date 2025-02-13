package com.techietaka.shoppingapp.productlist.data.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class ProductDto(
    @JsonNames("id")
    val id: Int,
    @JsonNames("title")
    val title: String,
    @JsonNames("price")
    val price: Double,
    @JsonNames("description")
    val description: String,
    @JsonNames("category")
    val category: String,
    @JsonNames("image")
    val image: String,
    @JsonNames("rating")
    val rating: RatingDto,
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class RatingDto(
    @JsonNames("rate")
    val rate: Double,
    @JsonNames("count")
    val count: Int,
)
