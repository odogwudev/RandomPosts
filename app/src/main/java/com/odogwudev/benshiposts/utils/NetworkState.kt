package com.odogwudev.benshiposts.utils

sealed class NetworkState<T> {
    data class Success<T>(val body: T, val message: String? = null) : NetworkState<T>()
    data class Error<T>(val body: T? = null, val message: String? = null) : NetworkState<T>()
}
