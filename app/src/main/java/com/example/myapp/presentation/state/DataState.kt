package com.example.myapp.presentation.state

sealed class DataState {
    object Idle : DataState()
    object Loading : DataState()
    data class Success(val data: String) : DataState()
    data class Error(val message: String) : DataState()
}