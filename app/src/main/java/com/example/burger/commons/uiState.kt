package com.example.burger.commons

sealed class uiState<out T> {
    object Loading : uiState<Nothing>()
    data class Success<T>(val data: T) : uiState<T>()
    data class Error(val data: Throwable) : uiState<Nothing>()
}

