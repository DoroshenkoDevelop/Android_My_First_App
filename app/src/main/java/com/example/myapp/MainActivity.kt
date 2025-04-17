package com.example.myapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*Задача 3: Замена одного фрагмента на другой (replace)
        Задание: В MainActivity есть два фрагмента (FragmentA, FragmentB). При нажатии
        на кнопку заменяй FragmentA на FragmentB*/
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, AFragment())
                .commit()
        }
        findViewById<Button>(R.id.btn_replace).setOnClickListener {
            // Заменяем FragmentA на FragmentB
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
                .replace(R.id.fragment_container, BFragment())
                .addToBackStack("fragment_b")
                .commit()
        }
        /*Задача 3: Замена одного фрагмента на другой (replace)
                Задание: В MainActivity есть два фрагмента (FragmentA, FragmentB). При нажатии
                на кнопку заменяй FragmentA на FragmentB*/
    }
}