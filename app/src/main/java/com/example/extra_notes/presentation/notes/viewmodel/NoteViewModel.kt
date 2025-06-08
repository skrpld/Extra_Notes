package com.example.extra_notes.presentation.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.extra_notes.data.local.db.Note
import com.example.extra_notes.data.local.db.NoteRoomDatabase
import com.example.extra_notes.data.repository.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val noteList: StateFlow<List<Note>>
    private val repository: NoteRepository

    init {
        val noteDb = NoteRoomDatabase.getInstance(application)
        val noteDao = noteDb.noteDao()
        repository = NoteRepository(noteDao)

        noteList = repository.noteList
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    }
}
