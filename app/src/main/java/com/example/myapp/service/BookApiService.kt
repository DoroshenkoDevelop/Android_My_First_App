package com.example.myapp.service

import com.example.myapp.model.BookDetailResponse
import com.example.myapp.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApiService {
    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String): BookResponse


    @GET("volumes/{id}")
    suspend fun getBookDetails(@Path("id") id: String): BookDetailResponse
}