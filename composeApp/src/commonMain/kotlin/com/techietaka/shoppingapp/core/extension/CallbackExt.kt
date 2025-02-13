package com.techietaka.shoppingapp.core.extension

// Type-alias for a callback that takes no arguments and returns nothing (Unit)
typealias EmptyCallback = () -> Unit

// Type-alias for a callback that takes one argument of type Type and returns nothing (Unit)
typealias SingleValueCallback<Type> = (value: Type) -> Unit