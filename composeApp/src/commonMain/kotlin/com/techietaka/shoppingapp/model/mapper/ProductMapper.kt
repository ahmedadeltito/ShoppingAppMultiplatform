package com.techietaka.shoppingapp.model.mapper

import com.techietaka.shoppingapp.model.dto.ProductDto
import com.techietaka.shoppingapp.model.dto.RatingDto
import com.techietaka.shoppingapp.model.entity.ProductEntity
import com.techietaka.shoppingapp.model.entity.RatingEntity

fun List<ProductDto>.toEntity(): List<ProductEntity> = with(receiver = this) {
    map { it.toEntity() }
}

fun ProductDto.toEntity(): ProductEntity =
    with(receiver = this) {
        ProductEntity(
            id = id,
            title = title,
            price = price,
            description = description,
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