package com.example.myapp.domain.usecase

import com.example.myapp.domain.entity.Note
import com.example.myapp.domain.repository.NoteRepository

// domain/usecase/GetNotesUseCase.kt
class GetNotesUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(): List<Note> = repository.getNotes()
}

// domain/usecase/AddNoteUseCase.kt
class AddNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.addNote(note)
}