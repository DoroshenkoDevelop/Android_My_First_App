package com.example.myapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.ItemBookBinding
import com.example.myapp.model.Book

class BookAdapter(private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private var books = emptyList<Book>()

    inner class ViewHolder(val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.binding.tvTitle.text = book.volumeInfo.title
        holder.binding.tvAuthor.text = book.volumeInfo.authors?.joinToString() ?: "Unknown"
        holder.itemView.setOnClickListener { onClick(book.id) }
    }

    override fun getItemCount() = books.size

    fun submitList(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }
}