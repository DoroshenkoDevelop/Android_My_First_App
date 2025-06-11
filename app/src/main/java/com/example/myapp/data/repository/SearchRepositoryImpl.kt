package com.example.myapp.data.repository

import com.example.myapp.data.datasource.SearchDataSource
import com.example.myapp.domain.model.SearchResult
import com.example.myapp.domain.repository.SearchRepository

class SearchRepositoryImpl(private val dataSource: SearchDataSource) : SearchRepository {
    override suspend fun search(query: String): SearchResult {
        return try {
            val results = dataSource.search(query)
            if (results.isEmpty()) SearchResult.Empty
            else SearchResult.Success(results)
        } catch (e: Exception) {
            SearchResult.Error(e.message ?: "Неизвестная ошибка")
        }
    }
}