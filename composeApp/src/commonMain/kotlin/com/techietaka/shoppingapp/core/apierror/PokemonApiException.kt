package com.techietaka.shoppingapp.core.apierror

enum class ShoppingAppExceptionStatus {
    ServiceUnavailable,
    ClientError,
    ServerError,
    UnknownError
}

class ShoppingAppException(
    error: ShoppingAppExceptionStatus
) : Exception("Something goes wrong: $error")