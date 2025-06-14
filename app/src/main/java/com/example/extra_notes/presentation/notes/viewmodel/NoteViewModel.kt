package com.example.extra_notes.presentation.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.extra_notes.data.local.db.Note
import com.example.extra_notes.data.local.db.NoteRoomDatabase
import com.example.extra_notes.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList: StateFlow<List<Note>> = _noteList.asStateFlow()

    private val repository: NoteRepository

    init {
        val noteDb = NoteRoomDatabase.getInstance(getApplication<Application>().applicationContext)
        val noteDao = noteDb.noteDao()
        repository = NoteRepository(noteDao)

        repository.noteList
            .onEach { notes ->
                _noteList.value = notes
            }
            .launchIn(viewModelScope)
    }

    fun addNote(title: String, content: String, isPinned: Boolean) {
        viewModelScope.launch {
            repository.addNote(Note(title = title, content = content, isPinned = isPinned))
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun getNoteById(noteId: Int) {
        viewModelScope.launch {
            // Логика получения одной заметки, если нужно
            // val note = repository.getNoteById(noteId)
            // _selectedNote.value = note // Если у вас есть StateFlow для выбранной заметки
        }
    }
}