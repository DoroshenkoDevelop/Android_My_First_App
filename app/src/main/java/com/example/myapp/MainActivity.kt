package com.example.myapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {

  //  private val textView = findViewById<TextView>(R.id.textView_2) // нельзя инициализировать view перед методом setContentView
 //   private var textView: TextView? = null
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        setText("С первой активити")
        textView.setOnClickListener { // слушатель по нажатию вызывает другой активити
            val intent = Intent(this, MainActivitySecond::class.java)
            intent.putExtra("massages", textView.text.toString())
            startActivity(intent)
            val userName = "Pavel"
            val userId = 6
            val message = getString(R.string.welcome_message, userName, userId)
            setText(message)
        }
    }

    private fun setUpView() {
        textView = findViewById(R.id.textView_2)
    }

    private fun setText(text: String) {
      //  textView?.text = text
          textView.text = text
    }
}