package com.example.myapp

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class AFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Получаем переданные данные
        val receivedData = arguments?.getString("KEY_DATA") ?: "Default Value"

        return TextView(requireContext()).apply {
            text = "Полученные данные: $receivedData"
            textSize = 24f
            gravity = Gravity.CENTER
            setTextColor(Color.BLUE)
        }
    }
}