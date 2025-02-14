package com.techietaka.shoppingapp.productdetails.presentation.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techietaka.shoppingapp.core.extension.EmptyCallback
import com.techietaka.shoppingapp.productdetails.presentation.udf.ProductDetailsEvent
import com.techietaka.shoppingapp.productdetails.presentation.udf.ProductDetailsSideEffect
import com.techietaka.shoppingapp.productdetails.presentation.udf.ProductDetailsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = koinViewModel<ProductDetailsViewModel>(),
    productId: Int,
    navigateBack: EmptyCallback
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        with(viewModel) {
            viewModel.onEvent(event = ProductDetailsEvent.GetProduct(productId = productId))
            sideEffectFlow.receiveAsFlow().collectLatest { sideEffect ->
                when (sideEffect) {
                    ProductDetailsSideEffect.NavigateToBack -> navigateBack()
                    is ProductDetailsSideEffect.ShowSnackBar -> snackbarHostState.showSnackbar(
                        message = sideEffect.message
                    )
                }
            }
        }
    }

    ProductDetailsUiComponent(
        snackbarHostState = snackbarHostState,
        productState = state,
        onBackButtonClicked = navigateBack,
        productAddToCart = { addToCartProduct ->
            viewModel.sendSideEffect(
                sideEffect = ProductDetailsSideEffect.ShowSnackBar(
                    message = "Product Id: $addToCartProduct added to cart"
                )
            )
        }
    )
}