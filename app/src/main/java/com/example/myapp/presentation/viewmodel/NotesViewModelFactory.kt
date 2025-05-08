package com.example.myapp.presentation.viewmodel

import NotesViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapp.domain.usecase.AddNoteUseCase
import com.example.myapp.domain.usecase.GetNotesUseCase

// presentation/viewmodel/NotesViewModelFactory.kt
class NotesViewModelFactory(
    private val getNotesUseCase: GetNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(getNotesUseCase, addNoteUseCase) as T
    }
}