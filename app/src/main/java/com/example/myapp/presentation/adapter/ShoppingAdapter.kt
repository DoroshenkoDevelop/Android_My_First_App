package com.example.myapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.ItemShoppingBinding
import com.example.myapp.domain.models.ShoppingItem

class ShoppingAdapter(
    private val onItemClick: (ShoppingItem) -> Unit
) : RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {

    private var items = emptyList<ShoppingItem>()

    inner class ViewHolder(val binding: ItemShoppingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShoppingItem) {
            binding.apply {
                // Отключаем слушатель перед обновлением состояния
                checkbox.setOnCheckedChangeListener(null)
                checkbox.isChecked = item.isPurchased
                checkbox.setOnCheckedChangeListener { _, isChecked ->
                    onItemClick(item.copy(isPurchased = isChecked))
                }
                itemName.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemShoppingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun updateItems(newItems: List<ShoppingItem>) {
        val diffCallback = /* Используйте DiffUtil, если список большой */
            newItems.also { items = it }
        notifyDataSetChanged() // Или notifyItemChanged(position)
    }
}

