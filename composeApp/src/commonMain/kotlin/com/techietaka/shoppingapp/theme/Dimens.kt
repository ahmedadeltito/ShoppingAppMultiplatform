package com.techietaka.shoppingapp.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Dimens(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp,
    val spriteImageHeight: Dp = 120.dp,
    val searchBarHeight: Dp = 56.dp,
    val sp12: TextUnit = 12.sp,
    val sp14: TextUnit = 14.sp,
    val sp16: TextUnit = 16.sp
)

val LocalDimens = compositionLocalOf { Dimens() }

/**
 * Retrieves the current [Dimens] at the call site's position in the hierarchy.
 */
@Suppress("UnusedReceiverParameter")
val MaterialTheme.dimens: Dimens
    @Composable
    @ReadOnlyComposable
    get() = LocalDimens.current
