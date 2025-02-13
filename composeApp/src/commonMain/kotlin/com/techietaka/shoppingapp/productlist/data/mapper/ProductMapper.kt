package com.techietaka.shoppingapp.productlist.data.mapper

import com.techietaka.shoppingapp.productlist.data.dto.ProductDto
import com.techietaka.shoppingapp.productlist.data.dto.RatingDto
import com.techietaka.shoppingapp.productlist.presentation.entity.ProductEntity
import com.techietaka.shoppingapp.productlist.presentation.entity.RatingEntity

fun List<ProductDto>.toEntity(): List<ProductEntity> = with(receiver = this) {
    map { it.toEntity() }
}

fun ProductDto.toEntity(): ProductEntity =
    with(receiver = this) {
        ProductEntity(
            id = id,
            title = title,
            price = price,
            category = category,
            image = image,
            rating = rating.toEntity(),
        )
    }

fun RatingDto.toEntity(): RatingEntity =
    with(receiver = this) {
        RatingEntity(
            rate = rate,
            count = count,
        )
    }