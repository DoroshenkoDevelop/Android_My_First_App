import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.domain.entity.Note
import com.example.myapp.domain.usecase.AddNoteUseCase
import com.example.myapp.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.launch

// presentation/viewmodel/NotesViewModel.kt
class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {
    private val _notes = MutableLiveData<List<Note>?>()
    val notes: MutableLiveData<List<Note>?> = _notes

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadNotes()
    }

    fun addNote(text: String) {
        if (text.isBlank()) {
            _error.value = "Note cannot be empty"
            return
        }

        viewModelScope.launch {
            try {
                addNoteUseCase(Note(text = text))
                loadNotes()
            } catch (e: Exception) {
                _error.value = "Failed to save note"
            }
        }
    }

    private fun loadNotes() {
        viewModelScope.launch {
            try {
                _notes.value = getNotesUseCase()
            } catch (e: Exception) {
                _error.value = "Failed to load notes"
            }
        }
    }
}