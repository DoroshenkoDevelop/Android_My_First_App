package com.example.myapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.adapter.BookAdapter
import com.example.myapp.databinding.ActivityMainBinding
import com.example.myapp.model.VolumeInfo
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadBooks()
    }

    private fun setupRecyclerView() {
        adapter = BookAdapter { bookId ->
            loadBookDetails(bookId)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadBooks() {
        lifecycleScope.launch {
            try {
                val response = RetrofitClient.apiService.getBooks("android")
                adapter.submitList(response.items)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadBookDetails(id: String) {
        lifecycleScope.launch {
            try {
                val book = RetrofitClient.apiService.getBookDetails(id)
                showBookDetails(book.volumeInfo)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showBookDetails(book: VolumeInfo) {
        AlertDialog.Builder(this)
            .setTitle(book.title)
            .setMessage("Authors: ${book.authors?.joinToString()}\n\n${book.description}")
            .setPositiveButton("OK", null)
            .show()
    }
}