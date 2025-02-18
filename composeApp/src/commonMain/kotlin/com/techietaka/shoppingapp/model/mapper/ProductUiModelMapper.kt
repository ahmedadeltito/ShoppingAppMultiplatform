package com.techietaka.shoppingapp.model.mapper

import com.techietaka.shoppingapp.model.entity.ProductEntity
import com.techietaka.shoppingapp.model.entity.RatingEntity
import com.techietaka.shoppingapp.model.ui.ProductUiModel
import com.techietaka.shoppingapp.model.ui.RatingUiModel

fun List<ProductEntity>.toUiModel(): List<ProductUiModel> = with(receiver = this) {
    map { it.toUiModel() }
}

fun ProductEntity.toUiModel(): ProductUiModel =
    with(receiver = this) {
        ProductUiModel(
            id = id,
            title = title,
            price = price,
            description = description,
            category = category,
            image = image,
            rating = rating.toUiModel(),
        )
    }

fun RatingEntity.toUiModel(): RatingUiModel =
    with(receiver = this) {
        RatingUiModel(
            rate = rate,
            count = count,
        )
    }