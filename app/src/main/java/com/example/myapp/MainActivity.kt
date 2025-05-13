package com.example.myapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.data.repository.ShoppingRepositoryImpl
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.domain.repository.ShoppingRepository
import com.example.myapp.domain.usecase.ShoppingUseCase
import com.example.myapp.presentation.adapter.ShoppingAdapter
import com.example.myapp.presentation.viewmodel.ShoppingViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ShoppingAdapter
    private val viewModel: ShoppingViewModel by lazy {
        (application as ShoppingApplication).viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        setupListeners()
    }

    private fun setupRecyclerView() {
        adapter = ShoppingAdapter { item ->
            viewModel.togglePurchaseStatus(item)
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupObservers() {
        viewModel.items.observe(this) { items ->
            adapter.updateItems(items)
        }
    }

    private fun setupListeners() {
        binding.addButton.setOnClickListener {
            val itemName = binding.inputField.text.toString().trim()
            if (itemName.isNotEmpty()) {
                viewModel.addItem(itemName)
                binding.inputField.text?.clear()
            }
        }
    }
}