import com.example.myapp.data.datasource.FileDataSource
import com.example.myapp.data.model.NoteModel
import com.example.myapp.domain.entity.Note
import com.example.myapp.domain.repository.NoteRepository
import java.util.Date

class NoteRepositoryImpl(
    private val fileDataSource: FileDataSource
) : NoteRepository {
    private val notes = mutableListOf<Note>()

    override suspend fun getNotes(): List<Note> {
        if (notes.isEmpty()) {
            val savedNotes = fileDataSource.readNotes()
            notes.addAll(savedNotes.map { it.toDomain() })
        }
        return notes.toList()
    }

    override suspend fun addNote(note: Note) {
        notes.add(note)
        saveNotes(notes)
    }

    override suspend fun saveNotes(notes: List<Note>) {
        fileDataSource.writeNotes(notes.map { it.toModel() })
    }

    private fun Note.toModel() = NoteModel(id, text, createdAt.time)
    private fun NoteModel.toDomain() = Note(id, text, Date(createdAt))
}