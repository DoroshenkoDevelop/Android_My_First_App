package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
        }
        startActivity(intent)
        setText("My text")
   /*     startActivity()*/
    }

  //  val color = R.color.black
   // val colors = R.string.first_string
  //  val color = resources.getColor(R.color.black)
 //   val string = getString();
    private fun setText(text: String) {
        textView.text = text
    }
}