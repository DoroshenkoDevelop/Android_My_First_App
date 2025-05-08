package com.example.myapp.domain.repository

import com.example.myapp.domain.entity.Note

interface NoteRepository {
    suspend fun getNotes(): List<Note>
    suspend fun addNote(note: Note)
    suspend fun saveNotes(notes: List<Note>)
}