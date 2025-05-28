package com.example.myapp.data

import kotlinx.coroutines.delay

class DataRepository {
    suspend fun fetchData(): String {
        val delayTime = (2000L..3000L).random()
        delay(delayTime)

        if ((0..100).random() < 30) {
            throw Exception("Ошибка загрузки: сервер не отвечает")
        }

        return "Данные загружены ($delayTime мс)"
    }
}
