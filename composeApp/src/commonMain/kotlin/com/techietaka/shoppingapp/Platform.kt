package com.techietaka.shoppingapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform