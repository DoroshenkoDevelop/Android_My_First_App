package com.example.myapp

// ShoppingApplication.kt
import android.app.Application
import com.example.myapp.data.repository.ShoppingRepositoryImpl
import com.example.myapp.domain.repository.ShoppingRepository
import com.example.myapp.domain.usecase.ShoppingUseCase
import com.example.myapp.presentation.viewmodel.ShoppingViewModel

class ShoppingApplication : Application() {
    private val shoppingRepository: ShoppingRepository by lazy { ShoppingRepositoryImpl() }
    private val shoppingUseCase: ShoppingUseCase by lazy { ShoppingUseCase(shoppingRepository) }
    val viewModel: ShoppingViewModel by lazy { ShoppingViewModel(shoppingUseCase) }
}