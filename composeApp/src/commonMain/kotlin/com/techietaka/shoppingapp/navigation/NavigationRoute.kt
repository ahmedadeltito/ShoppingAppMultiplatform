package com.techietaka.shoppingapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.techietaka.shoppingapp.core.animation.customFadeIn
import com.techietaka.shoppingapp.core.animation.customFadeOut
import com.techietaka.shoppingapp.core.extension.EmptyCallback
import com.techietaka.shoppingapp.core.extension.SingleValueCallback
import com.techietaka.shoppingapp.productdetails.presentation.ui.ProductDetailsScreen
import com.techietaka.shoppingapp.productlist.presentation.ui.ProductListScreen

internal object NavigationRoute {

    fun NavGraphBuilder.productListScreen(onNavigateToProductDetails: SingleValueCallback<Int>) {
        composable<ProductListScreen>(
            enterTransition = { customFadeIn() },
            exitTransition = { customFadeOut() }
        ) {
            ProductListScreen(navigateToProductDetails = onNavigateToProductDetails)
        }
    }

    fun NavGraphBuilder.productDetailsScreen(onBackAction: EmptyCallback) {
        composable<ProductDetailsScreen>(
            enterTransition = { customFadeIn() },
            exitTransition = { customFadeOut() }
        ) { backStackEntry ->
            val args = backStackEntry.toRoute<ProductDetailsScreen>()
            val productId = args.productId
            if (productId > 0) {
                ProductDetailsScreen(
                    productId = productId,
                    navigateBack = onBackAction
                )
            }
        }
    }
}
