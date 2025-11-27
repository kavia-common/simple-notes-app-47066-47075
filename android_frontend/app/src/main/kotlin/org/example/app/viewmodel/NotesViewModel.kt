package org.example.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.example.app.data.NotesRepository
import org.example.app.model.Note

data class NotesUiState(
    val notes: List<Note> = emptyList(),
    val currentEditing: Note? = null
)

/**
 * PUBLIC_INTERFACE
 * ViewModel managing notes list and the currently edited note.
 */
class NotesViewModel(
    private val repository: NotesRepository = NotesRepository()
) : ViewModel() {

    private val notesFlow = repository.getNotes()

    private val currentNoteFlow: StateFlow<Note?> =
        kotlinx.coroutines.flow.MutableStateFlow<Note?>(null).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    val uiState: NotesUiState by lazy {
        val combined: StateFlow<NotesUiState> = combine(
            notesFlow,
            currentNoteFlow
        ) { notes, current ->
            NotesUiState(notes = notes, currentEditing = current)
        }.stateIn(viewModelScope, SharingStarted.Eagerly, NotesUiState())
        CombinedState(combined)
    }.state

    private inner class CombinedState(private val flow: StateFlow<NotesUiState>) {
        val state: NotesUiState
            get() = flow.value
    }

    private val mutableCurrent = kotlinx.coroutines.flow.MutableStateFlow<Note?>(null)

    init {
        // Tie the public state to combined one
        // This is a small helper to expose uiState as a snapshot property
    }

    fun prepareNewNote() {
        mutableCurrent.value = Note()
        setCurrent(mutableCurrent.value)
    }

    fun loadNote(id: String) {
        mutableCurrent.value = repository.findById(id)
        setCurrent(mutableCurrent.value)
    }

    fun updateTitle(newTitle: String) {
        mutableCurrent.value = (mutableCurrent.value ?: Note()).copy(title = newTitle)
        setCurrent(mutableCurrent.value)
    }

    fun updateContent(newContent: String) {
        mutableCurrent.value = (mutableCurrent.value ?: Note()).copy(content = newContent)
        setCurrent(mutableCurrent.value)
    }

    fun saveCurrent() {
        mutableCurrent.value?.let {
            repository.upsert(
                it.copy(timestamp = System.currentTimeMillis())
            )
        }
        mutableCurrent.value = null
        setCurrent(null)
    }

    fun deleteCurrent() {
        mutableCurrent.value?.id?.let { repository.delete(it) }
        mutableCurrent.value = null
        setCurrent(null)
    }

    fun deleteNote(id: String) {
        repository.delete(id)
        if (mutableCurrent.value?.id == id) {
            mutableCurrent.value = null
            setCurrent(null)
        }
    }

    private fun setCurrent(note: Note?) {
        // Reflect to the state flow used for combination
        (currentNoteFlow as? kotlinx.coroutines.flow.MutableStateFlow)?.value = note
    }
}
