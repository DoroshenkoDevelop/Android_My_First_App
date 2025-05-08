package com.example.myapp

import NoteRepositoryImpl
import NotesViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.data.datasource.FileDataSource
import com.example.myapp.databinding.ActivityNotesBinding
import com.example.myapp.domain.usecase.AddNoteUseCase
import com.example.myapp.domain.usecase.GetNotesUseCase
import com.example.myapp.presentation.adapter.NotesAdapter
import com.example.myapp.presentation.viewmodel.NotesViewModelFactory


// presentation/view/NotesActivity.kt
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotesBinding
    private lateinit var viewModel: NotesViewModel
    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupRecyclerView()
        setupObservers()
        setupListeners()
    }

    private fun setupViewModel() {
        val repository = NoteRepositoryImpl(FileDataSource(applicationContext))
        viewModel = ViewModelProvider(
            this,
            NotesViewModelFactory(
                GetNotesUseCase(repository),
                AddNoteUseCase(repository)
            )
        )[NotesViewModel::class.java]
    }

    private fun setupRecyclerView() {
        adapter = NotesAdapter()
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupObservers() {
        viewModel.notes.observe(this) { notes ->
            if (notes != null) {
                adapter.submitList(notes)
            }
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.error.value
            }
        }
    }

    private fun setupListeners() {
        binding.btnAdd.setOnClickListener {
            viewModel.addNote(binding.etNote.text.toString())
            binding.etNote.text.clear()
        }
    }
}