package com.odogwudev.benshiposts.shared.utils

sealed class UIState<T> {
    data class Success<T>(val data: T, val message: String? = null) : UIState<T>()
    data class Loading<T>(val data: T? = null) : UIState<T>()
    data class Error<T>(val message: String? = null) : UIState<T>()
}
