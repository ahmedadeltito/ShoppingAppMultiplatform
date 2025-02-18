package com.techietaka.shoppingapp.productdetails.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techietaka.shoppingapp.core.extension.EmptyCallback
import com.techietaka.shoppingapp.core.extension.SingleValueCallback
import com.techietaka.shoppingapp.core.uicomponent.AsyncImage
import com.techietaka.shoppingapp.core.uicomponent.EmptyComponent
import com.techietaka.shoppingapp.core.uicomponent.LoadingAnimation
import com.techietaka.shoppingapp.core.uicomponent.RatingStar
import com.techietaka.shoppingapp.core.uicomponent.TitleBar
import com.techietaka.shoppingapp.model.ui.ProductUiModel
import com.techietaka.shoppingapp.productdetails.presentation.udf.ProductDetailsUiState
import com.techietaka.shoppingapp.theme.dimens

@Composable
fun ProductDetailsUiComponent(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    productState: ProductDetailsUiState,
    onBackButtonClicked: EmptyCallback = {},
    productAddToCart: SingleValueCallback<Int> = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TitleBar(
                toolbarTitle = productState.product.title,
                navigationButtonAction = onBackButtonClicked
            )
            with(productState) {
                if (errorMessage.isNotBlank()) {
                    EmptyComponent(message = errorMessage)
                }
                if (isLoading) {
                    LoadingAnimation(circleSize = MaterialTheme.dimens.medium)
                }
                if (product.id == -1) {
                    EmptyComponent(message = "No Product Found")
                } else {
                    ProductItem(
                        modifier = modifier,
                        product = product,
                        onAddToCartClicked = productAddToCart
                    )
                }
            }
        }
        SnackbarHost(
            hostState = snackbarHostState,
            modifier =
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun ColumnScope.ProductItem(
    modifier: Modifier = Modifier,
    product: ProductUiModel,
    onAddToCartClicked: SingleValueCallback<Int>
) {
    Box(modifier = modifier.weight(2f).padding(top = 16.dp), contentAlignment = Alignment.Center) {
        AsyncImage(
            url = product.image,
            contentDescription = product.title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
    Card(
        modifier = modifier
            .fillMaxWidth()
            .weight(2f)
            .verticalScroll(rememberScrollState()),
        shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
    ) {
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = product.title,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.height(12.dp))

            val rating: Float by remember { mutableStateOf(product.rating.rate.toFloat()) }

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingStar(rating = rating)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "(${product.rating.count})",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = product.description,
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )

        }
        Row(
            modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "$${product.price}",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = { onAddToCartClicked(product.id) },
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    text = "Add To Cart"
                )
            }
        }
    }
}