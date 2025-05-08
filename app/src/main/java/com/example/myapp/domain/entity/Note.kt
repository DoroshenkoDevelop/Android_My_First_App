package com.example.myapp.domain.entity

import java.util.Date

data class Note(
    val id: Long = System.currentTimeMillis(),
    val text: String,
    val createdAt: Date = Date()
)
