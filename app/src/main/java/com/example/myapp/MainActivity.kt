package com.example.myapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* Задача 2:  Добавление фрагмента в контейнер (FragmentManager)
        Задание: Добавь MyFragment в контейнер FrameLayout*/
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, MyFragment())
                .commit()
        }
        /* Задача 2:  Добавление фрагмента в контейнер (FragmentManager)
        Задание: Добавь MyFragment в контейнер FrameLayout*/
    }
}