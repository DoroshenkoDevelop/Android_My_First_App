package com.example.myapp.domain.models

data class ShoppingItem(
    val id: Int = 0,
    val name: String,
    val isPurchased: Boolean = false
)
