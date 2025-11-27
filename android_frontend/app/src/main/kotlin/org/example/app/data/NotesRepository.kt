package org.example.app.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.example.app.model.Note

/**
 * PUBLIC_INTERFACE
 * Repository for managing notes locally in memory.
 * Replaceable with Room or DataStore without changing the UI layer.
 */
class NotesRepository {

    private val notesFlow = MutableStateFlow<List<Note>>(sampleNotes())

    fun getNotes(): StateFlow<List<Note>> = notesFlow

    fun upsert(note: Note) {
        notesFlow.update { list ->
            val idx = list.indexOfFirst { it.id == note.id }
            if (idx >= 0) {
                list.toMutableList().also { it[idx] = note }
            } else {
                list + note
            }
        }
    }

    fun delete(id: String) {
        notesFlow.update { list ->
            list.filterNot { it.id == id }
        }
    }

    fun findById(id: String): Note? = notesFlow.value.firstOrNull { it.id == id }

    private fun sampleNotes(): List<Note> = listOf(
        Note(title = "Welcome to Simple Notes", content = "Tap the + to add a new note.\nSwipe the card menu to delete.", timestamp = System.currentTimeMillis() - 86400000),
        Note(title = "Ocean Professional", content = "Primary: #2563EB\nSecondary: #F59E0B\nError: #EF4444", timestamp = System.currentTimeMillis() - 43200000),
        Note(title = "Compose + MVVM", content = "This app uses Material 3, Compose, and a simple MVVM structure.", timestamp = System.currentTimeMillis() - 120000)
    )
}
