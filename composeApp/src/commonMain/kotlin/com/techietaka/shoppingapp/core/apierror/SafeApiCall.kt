package com.techietaka.shoppingapp.core.apierror

import com.techietaka.shoppingapp.core.dispatcher.shoppingAppDispatchers
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.withContext

suspend inline fun <reified T> safeApiCall(
    crossinline response: suspend () -> HttpResponse
): T = withContext(shoppingAppDispatchers.io) {

    val result = try {
        response()
    } catch (e: kotlinx.io.IOException) {
        throw ShoppingAppException(ShoppingAppExceptionStatus.ServiceUnavailable)
    }

    when (result.status.value) {
        in 200..299 -> Unit
        in 400..499 -> throw ShoppingAppException(ShoppingAppExceptionStatus.ClientError)
        500 -> throw ShoppingAppException(ShoppingAppExceptionStatus.ServerError)
        else -> throw ShoppingAppException(ShoppingAppExceptionStatus.UnknownError)
    }

    return@withContext try {
        result.body()
    } catch (e: Exception) {
        throw ShoppingAppException(ShoppingAppExceptionStatus.ServerError)
    }

}