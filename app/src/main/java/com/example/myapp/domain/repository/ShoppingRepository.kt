package com.example.myapp.domain.repository

import com.example.myapp.domain.models.ShoppingItem

interface ShoppingRepository {
    fun addItem(item: ShoppingItem)
    fun getItems(): List<ShoppingItem>
    fun updateItem(item: ShoppingItem)
}