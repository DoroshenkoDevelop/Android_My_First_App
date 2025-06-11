package com.example.myapp.data.datasource

import kotlinx.coroutines.delay

class SearchDataSource {
    suspend fun search(query: String): List<String> {

        delay(1000)

        if (query == "error") throw Exception("Произошла ошибка поиска")

        return if (query.isNotEmpty()) {
            listOf("$query result 1", "$query result 2", "$query result 3")
        } else {
            emptyList()
        }
    }
}