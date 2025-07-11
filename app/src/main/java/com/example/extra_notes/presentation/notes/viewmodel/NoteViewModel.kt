package com.example.extra_notes.presentation.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.extra_notes.data.local.db.Note
import com.example.extra_notes.data.local.db.NoteRoomDatabase
import com.example.extra_notes.data.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList: StateFlow<List<Note>> = _noteList.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val repository: NoteRepository

    init {
        val noteDb = NoteRoomDatabase.getInstance(getApplication<Application>().applicationContext)
        val noteDao = noteDb.noteDao()
        repository = NoteRepository(noteDao)

        viewModelScope.launch {
            _searchQuery.collectLatest { query ->
                repository.getNotes(query)
                    .onEach { notes ->
                        _noteList.value = notes
                    }
                    .launchIn(viewModelScope)
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
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

//    fun updateNote(note: Note) {
//        viewModelScope.launch {
//            repository.updateNote(note)
//            // Заставляем _searchQuery эмитить текущее значение, чтобы collectLatest сработал заново
//            val currentQuery = _searchQuery.value
//            _searchQuery.value = "" // Временно меняем, чтобы следующий вызов был "новым"
//            _searchQuery.value = currentQuery
//        }
//    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun getNoteById(id: Int): Flow<Note?> {
        return repository.getNoteById(id)
    }
}