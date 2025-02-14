package com.techietaka.shoppingapp.core.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

fun customFadeIn() = fadeIn(
    animationSpec = tween(
        durationMillis = 300,
        easing = LinearEasing
    )
)

fun customFadeOut() = fadeOut(
    animationSpec = tween(
        durationMillis = 300,
        easing = LinearEasing
    )
)