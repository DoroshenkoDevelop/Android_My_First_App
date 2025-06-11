package com.example.myapp.domain.repository

import com.example.myapp.domain.model.SearchResult

interface SearchRepository {
    suspend fun search(query: String): SearchResult
}