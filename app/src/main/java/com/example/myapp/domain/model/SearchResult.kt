package com.example.myapp.domain.model

sealed class SearchResult {
    data class Success(val data: List<String>) : SearchResult()
    data class Error(val message: String) : SearchResult()
    object Empty : SearchResult()
}