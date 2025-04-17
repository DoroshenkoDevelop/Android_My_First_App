package com.example.myapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        /*Задача 1: Запуск новой Activity с передачей данных через Intent
        Открыть SecondActivity из MainActivity, передав строку "Hello, Second Activity!"*/
        val message = intent.getStringExtra("EXTRA_MESSAGE") ?: "Default text"
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = message
        /*Задача 1: Запуск новой Activity с передачей данных через Intent
        Открыть SecondActivity из MainActivity, передав строку "Hello, Second Activity!"*/
    }
}