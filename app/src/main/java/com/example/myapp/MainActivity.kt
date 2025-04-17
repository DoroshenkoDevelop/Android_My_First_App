package com.example.myapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*Задача 1: Запуск новой Activity с передачей данных через Intent
        Открыть SecondActivity из MainActivity, передав строку "Hello, Second Activity!"*/
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("EXTRA_MESSAGE", "Hello, Second Activity!")
        }
        startActivity(intent)
        /*Задача 1: Запуск новой Activity с передачей данных через Intent
        Открыть SecondActivity из MainActivity, передав строку "Hello, Second Activity!"*/

    }
}