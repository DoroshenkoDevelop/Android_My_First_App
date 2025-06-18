package com.example.myapp.model

data class BookResponse(
    val items: List<Book>
)

data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val authors: List<String>?,
    val description: String?
)

data class BookDetailResponse(
    val volumeInfo: VolumeInfo
)