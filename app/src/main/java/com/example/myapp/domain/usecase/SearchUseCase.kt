package com.example.myapp.domain.usecase

import com.example.myapp.domain.model.SearchResult
import com.example.myapp.domain.repository.SearchRepository

class SearchUseCase(private val repository: SearchRepository) {
    suspend operator fun invoke(query: String): SearchResult {
        return repository.search(query)
    }
}