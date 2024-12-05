package com.rapptrlabs.androidtest.data.model

sealed interface UiState {
    data object Initial : UiState

    data class Loading(
        val message: String = "Please wait",
    ) : UiState

    data class Error(
        val message: String
    ) : UiState

    data object Empty : UiState

    data class Success(val message: String) : UiState
}