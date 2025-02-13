package com.techietaka.shoppingapp.productlist.data.remote

import com.techietaka.shoppingapp.core.apierror.safeApiCall
import com.techietaka.shoppingapp.productlist.data.dto.ProductDto
import com.techietaka.shoppingapp.productlist.data.mapper.toEntity
import com.techietaka.shoppingapp.productlist.presentation.entity.ProductEntity
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductListApiService(private val httpClient: HttpClient) {

    fun getProductList(): Flow<List<ProductEntity>> = flow {
        val productList = safeApiCall<List<ProductDto>> {
            httpClient.get(urlString = "products") {
                contentType(ContentType.Application.Json)
            }
        }
        emit(value = productList.toEntity())
    }

}