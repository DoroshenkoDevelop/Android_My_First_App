package com.example.myapp.presentation.ui.state

sealed class SearchState {
    object Idle : SearchState()
    object Loading : SearchState()
    data class Success(val data: List<String>) : SearchState()
    data class Error(val message: String) : SearchState()
    object Empty : SearchState()
}