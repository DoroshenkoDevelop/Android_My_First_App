package com.example.myapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.ItemNoteBinding
import com.example.myapp.domain.entity.Note
import java.text.SimpleDateFormat
import java.util.Locale

// presentation/adapter/NotesAdapter.kt
class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    private val notes = mutableListOf<Note>()

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.tvText.text = note.text
            binding.tvDate.text = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                .format(note.createdAt)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
        ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size

    fun submitList(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }
}