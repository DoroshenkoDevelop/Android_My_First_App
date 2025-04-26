package com.example.myapp
/*Задача 1: Динамическое добавление элементов в RecyclerView
При нажатии на кнопку добавлять новый элемент в список*/

/*Задача 2: Пустой экран в RecyclerView, если нет данных
Показать заглушку (например, TextView "Нет данных") вместо списка, если список пуст.*/
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter
    private val items = mutableListOf<Item>()
    private var itemCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupButtons()
        checkIfEmpty()
    }

    private fun setupRecyclerView() {
        adapter = MyAdapter(items, ::checkIfEmpty)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupButtons() {
        binding.fab.setOnClickListener {
            addNewItem()
        }

        binding.addFirstItemButton.setOnClickListener {
            addNewItem()
        }
    }
    private fun addNewItem() {
        itemCounter++
        adapter.addItem(Item(itemCounter, "Элемент $itemCounter"))
        binding.recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
    }

    private fun checkIfEmpty() {
        if (items.isEmpty()) {
            binding.recyclerView.visibility = View.GONE
            binding.emptyView.visibility = View.VISIBLE
        } else {
            binding.recyclerView.visibility = View.VISIBLE
            binding.emptyView.visibility = View.GONE
        }
    }
}