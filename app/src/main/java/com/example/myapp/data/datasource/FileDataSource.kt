package com.example.myapp.data.datasource

import android.content.Context
import com.example.myapp.data.model.NoteModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

// data/datasource/FileDataSource.kt
class FileDataSource(private val context: Context) {
    private val fileName = "notes.json"
    private val gson = Gson()

    suspend fun readNotes(): List<NoteModel> {
        return withContext(Dispatchers.IO) {
            try {
                val file = File(context.filesDir, fileName)
                if (!file.exists()) return@withContext emptyList()

                val json = file.readText()
                val type = object : TypeToken<List<NoteModel>>() {}.type
                gson.fromJson(json, type) ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    suspend fun writeNotes(notes: List<NoteModel>) {
        withContext(Dispatchers.IO) {
            try {
                val file = File(context.filesDir, fileName)
                file.writeText(gson.toJson(notes))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}