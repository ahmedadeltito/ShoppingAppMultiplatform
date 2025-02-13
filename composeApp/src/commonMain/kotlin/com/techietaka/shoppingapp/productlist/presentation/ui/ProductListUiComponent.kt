package com.techietaka.shoppingapp.productlist.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.techietaka.shoppingapp.core.extension.SingleValueCallback
import com.techietaka.shoppingapp.core.uicomponent.AsyncImage
import com.techietaka.shoppingapp.core.uicomponent.EmptyComponent
import com.techietaka.shoppingapp.core.uicomponent.LoadingAnimation
import com.techietaka.shoppingapp.core.uicomponent.TitleBar
import com.techietaka.shoppingapp.productlist.presentation.entity.ProductEntity
import com.techietaka.shoppingapp.productlist.presentation.udf.ProductListState
import com.techietaka.shoppingapp.theme.Yellow400
import com.techietaka.shoppingapp.theme.dimens
import org.jetbrains.compose.resources.painterResource
import shoppingappmultiplatform.composeapp.generated.resources.Res
import shoppingappmultiplatform.composeapp.generated.resources.ic_star

@Composable
fun ProductListUiComponent(
    snackbarHostState: SnackbarHostState,
    productListState: ProductListState,
    navigateToPokemonDetails: SingleValueCallback<Int>
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            TitleBar(toolbarTitle = "Product List")
            with(productListState) {
                if (errorMessage.isNotBlank()) {
                    EmptyComponent(message = errorMessage)
                }
                if (isLoading) {
                    LoadingAnimation(circleSize = MaterialTheme.dimens.medium)
                }
                if (productList.isEmpty()) {
                    EmptyComponent(message = "No Product Found")
                } else {
                    ProductListGrid(
                        onProductClicked = navigateToPokemonDetails,
                        productList = productListState.productList,
                        modifier = Modifier.fillMaxSize()
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
private fun ProductListGrid(
    onProductClicked: SingleValueCallback<Int>,
    productList: List<ProductEntity>,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints {
        val columns = when (maxWidth) {
            in 0.dp..349.dp -> 1
            in 350.dp..599.dp -> 2
            in 600.dp..899.dp -> 3
            in 900.dp..1199.dp -> 4
            else -> 5
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.medium),
            contentPadding = PaddingValues(MaterialTheme.dimens.medium),
            modifier = modifier,
        ) {
            items(productList, key = { it.id }) { product ->
                ProductItem(
                    navigateToProductDetails = onProductClicked,
                    product = product,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun ProductItem(
    product: ProductEntity,
    navigateToProductDetails: SingleValueCallback<Int>,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(MaterialTheme.dimens.extraSmall)
            .clickable { navigateToProductDetails(product.id) },
        shape = RoundedCornerShape(MaterialTheme.dimens.small)
    ) {
        AsyncImage(
            url = product.image,
            contentDescription = product.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.8f)
                .fillMaxHeight()
        )
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.small))
        Column(
            modifier = Modifier.padding(
                start = MaterialTheme.dimens.medium,
                end = MaterialTheme.dimens.medium
            ),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = product.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = MaterialTheme.dimens.sp14,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.extraSmall))
            Text(
                text = product.category,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = MaterialTheme.dimens.sp12,
                fontWeight = FontWeight.ExtraLight
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small))
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top,
            ) {
                Icon(
                    modifier = Modifier
                        .size(MaterialTheme.dimens.medium)
                        .align(CenterVertically),
                    painter = painterResource(Res.drawable.ic_star),
                    contentDescription = null,
                    tint = Yellow400
                )
                Text(
                    modifier = Modifier.align(CenterVertically)
                        .padding(start = MaterialTheme.dimens.small),
                    text = "${product.rating.rate} (${product.rating.count})",
                    fontSize = MaterialTheme.dimens.sp14,
                    fontWeight = FontWeight.Light
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.small))
            Text(
                text = "$${product.price}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = MaterialTheme.dimens.sp16,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium))
        }
    }
}

