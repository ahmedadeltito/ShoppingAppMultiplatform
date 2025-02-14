package com.techietaka.shoppingapp.productdetails.data.remote

import com.techietaka.shoppingapp.core.apierror.safeApiCall
import com.techietaka.shoppingapp.model.dto.ProductDto
import com.techietaka.shoppingapp.model.entity.ProductEntity
import com.techietaka.shoppingapp.model.mapper.toEntity
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductDetailsApiService(private val httpClient: HttpClient) {

    fun getProduct(productId: Int): Flow<ProductEntity> = flow {
        val product = safeApiCall<ProductDto> {
            httpClient.get(urlString = "products/$productId") {
                contentType(ContentType.Application.Json)
            }
        }
        emit(value = product.toEntity())
    }

}