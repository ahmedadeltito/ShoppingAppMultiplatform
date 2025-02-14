package com.techietaka.shoppingapp.productlist.presentation.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techietaka.shoppingapp.core.extension.SingleValueCallback
import com.techietaka.shoppingapp.productlist.presentation.udf.ProductListEvent
import com.techietaka.shoppingapp.productlist.presentation.udf.ProductListSideEffect
import com.techietaka.shoppingapp.productlist.presentation.udf.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = koinViewModel<ProductListViewModel>(),
    navigateToProductDetails: SingleValueCallback<Int>
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        with(viewModel) {
            if (state.productList.isEmpty()) {
                viewModel.onEvent(event = ProductListEvent.GetProductList)
            }
            sideEffectFlow.receiveAsFlow().collectLatest { sideEffect ->
                when (sideEffect) {
                    is ProductListSideEffect.NavigateToProductDetails ->
                        navigateToProductDetails(sideEffect.productId)

                    is ProductListSideEffect.ShowSnackBar ->
                        snackbarHostState.showSnackbar(message = sideEffect.message)
                }
            }
        }
    }

    ProductListUiComponent(
        snackbarHostState = snackbarHostState,
        productListState = state,
        navigateToProductDetails = { productId ->
            viewModel.sendSideEffect(
                sideEffect = ProductListSideEffect.NavigateToProductDetails(productId = productId)
            )
        },
    )
}