package com.example.myapp.data.repository

import com.example.myapp.domain.models.ShoppingItem
import com.example.myapp.domain.repository.ShoppingRepository

class ShoppingRepositoryImpl : ShoppingRepository {
    private val items = mutableListOf<ShoppingItem>()
    private var currentId = 0

    override fun addItem(item: ShoppingItem) {
        items.add(item.copy(id = ++currentId))
    }

    override fun getItems(): List<ShoppingItem> = items.toList()

    override fun updateItem(item: ShoppingItem) {
        val index = items.indexOfFirst { it.id == item.id }
        if (index != -1) {
            items[index] = item
        }
    }
}