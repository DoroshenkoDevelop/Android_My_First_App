package com.example.myapp.domain

import com.example.myapp.data.DataRepository

class FetchDataUseCase(private val repository: DataRepository) {
    suspend fun execute(): String = repository.fetchData()
}