package com.example.myapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.domain.models.ShoppingItem
import com.example.myapp.domain.usecase.ShoppingUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val shoppingUseCase: ShoppingUseCase
) : ViewModel() {
    private val _items = MutableLiveData<List<ShoppingItem>>()
    val items: LiveData<List<ShoppingItem>> = _items
    fun togglePurchaseStatus(item: ShoppingItem) {
        viewModelScope.launch {
            shoppingUseCase.togglePurchaseStatus(item)
            // Задержка для завершения анимации прокрутки
            delay(100) // 100 мс (можно уменьшить)
            _items.postValue(shoppingUseCase.getItems())
        }

    }
    init {
        loadItems()
    }
    fun addItem(name: String) {
        shoppingUseCase.addItem(name)
        loadItems()
    }
    private fun loadItems() {
        _items.value = shoppingUseCase.getItems()
    }
}