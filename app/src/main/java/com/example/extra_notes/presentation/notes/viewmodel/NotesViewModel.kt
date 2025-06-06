package com.example.extra_notes.presentation.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.extra_notes.data.local.db.Note
import com.example.extra_notes.data.repository.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NotesViewModel(private val repository: NotesRepository) : ViewModel() {

    // Состояния UI
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    private val _currentNote = MutableStateFlow<Note?>(null)
    val currentNote: StateFlow<Note?> = _currentNote.asStateFlow()

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    sealed class UiState {
        object Idle : UiState()
        object Loading : UiState()
        data class Success(val message: String) : UiState()
        data class Error(val message: String) : UiState()
    }

    init {
        loadAllNotes()
    }

    // Загрузка всех заметок
    private fun loadAllNotes() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                repository.getAllNotes().collectLatest { notesList ->
                    _notes.value = notesList
                    _uiState.value = UiState.Success("Notes loaded")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error loading notes: ${e.message}")
            }
        }
    }

    // Создание/обновление заметки
    fun saveNote(title: String, content: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val current = _currentNote.value
                if (current == null) {
                    // Новая заметка
                    val newNote = Note(
                        title = title,
                        content = content,
                        isPinned = false
                    )
                    repository.addNote(newNote)
                } else {
                    // Обновление существующей
                    val updatedNote = current.copy(
                        title = title,
                        content = content
                    )
                    repository.updateNote(updatedNote)
                }
                _uiState.value = UiState.Success("Note saved")
                _currentNote.value = null
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error saving note: ${e.message}")
            }
        }
    }

    // Удаление заметки
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                repository.deleteNote(note)
                _uiState.value = UiState.Success("Note deleted")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error deleting note: ${e.message}")
            }
        }
    }

    // Загрузка заметки по ID
    fun loadNoteById(id: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                _currentNote.value = repository.getNoteById(id)
                _uiState.value = UiState.Success("Note loaded")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error loading note: ${e.message}")
            }
        }
    }

    // Переключение статуса закрепления
    fun togglePinStatus(note: Note) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                repository.setPinned(note.id.toLong(), !note.isPinned)
                _uiState.value = UiState.Success("Pin status updated")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error updating pin: ${e.message}")
            }
        }
    }

    // Сброс текущей заметки
    fun resetCurrentNote() {
        _currentNote.value = null
    }
}