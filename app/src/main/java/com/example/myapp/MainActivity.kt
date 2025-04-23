package com.example.myapp
import DynamicPagerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DynamicPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*Задача 1: ViewPager2 с динамическими фрагментами
        Создай ViewPager2, в который можно добавлять фрагменты во время выполнения,
        например, при нажатии на кнопку.*/

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DynamicPagerAdapter(this)
        binding.viewPager.adapter = adapter

        adapter.addFragment(DynamicFragment.newInstance())

        binding.addFragmentButton.setOnClickListener {
            adapter.addFragment(DynamicFragment.newInstance())
            binding.viewPager.setCurrentItem(adapter.itemCount - 1, true)

        }
        binding.btnShowSnackbar.setOnClickListener {
            showSnackbarWithUndo(binding.root)
        }
    }
    /*Задача 2*: Snackbar с действием
    Показать Snackbar с сообщением и кнопкой "Отмена", которая отменяет действие.*/

    private fun showSnackbarWithUndo(view: View) {
        Snackbar.make(
            view,
            "Сообщение удалено",
            Snackbar.LENGTH_LONG
        ).setAction("ОТМЕНА") {
            Toast.makeText(this, "Удаление отменено!", Toast.LENGTH_SHORT).show()
        }.show()
    }
}