package com.techietaka.shoppingapp.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.techietaka.shoppingapp.navigation.NavigationRoute.productDetailsScreen
import com.techietaka.shoppingapp.navigation.NavigationRoute.productListScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ProductListScreen,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        productListScreen(
            onNavigateToProductDetails = { productId ->
                navController.navigate(ProductDetailsScreen(productId = productId))
            }
        )
        productDetailsScreen(onBackAction = { navController.popBackStack() })
    }
}

@Serializable
internal data object ProductListScreen

@Serializable
internal data class ProductDetailsScreen(val productId: Int)