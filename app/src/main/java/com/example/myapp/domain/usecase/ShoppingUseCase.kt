package com.example.myapp.domain.usecase

import com.example.myapp.domain.models.ShoppingItem
import com.example.myapp.domain.repository.ShoppingRepository

class ShoppingUseCase(private val repository: ShoppingRepository) {
    fun addItem(name: String) {
        repository.addItem(ShoppingItem(name = name))
    }

    fun getItems(): List<ShoppingItem> = repository.getItems()

    fun togglePurchaseStatus(item: ShoppingItem) {
        repository.updateItem(item.copy(isPurchased = !item.isPurchased))
    }
}