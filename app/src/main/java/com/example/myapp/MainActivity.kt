package com.example.myapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.adapter.PostAdapter
import com.example.myapp.model.PostItem


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: PostAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. RecyclerView и кнопки
        recyclerView = findViewById(R.id.recyclerView)
        refreshButton = findViewById(R.id.refreshButton)

        // 2. Адаптер
        adapter = PostAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // 3. Загрузка начальных данных
        loadInitialData()

        // 4. Обработка клика на кнопку обновления
        refreshButton.setOnClickListener {
            updateData()
        }
    }

    private fun loadInitialData() {
        val initialItems = listOf(
            PostItem.AuthorPost("Мужчина", "Привет как дела?"),
            PostItem.ImagePost("", "Красивое фото"),
            PostItem.TextWithButtonPost("Нравится пост?", "Лайк")
        )
        adapter.updateList(initialItems)
    }

    private fun updateData() {
        val updatedItems = listOf(
            PostItem.AuthorPost("Дувушка", "Спасибо все хорошо"),
            PostItem.TextWithButtonPost("Новый текст", "Комментировать"),
            PostItem.ImagePost("", "Еще одно фото")
        )
        adapter.updateList(updatedItems)
    }
}