package com.example.extra_notes.data.repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.example.extra_notes.data.local.db.Note
import com.example.extra_notes.data.local.db.NoteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteRepository(
    private val noteDao: NoteDao
) {
    val coincidence by mutableStateOf("")
    val noteList: Flow<List<Note>> = noteDao.getAllNotes(coincidence)
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun getNotes(searchQuery: String): Flow<List<Note>> {
        return noteDao.getAllNotes(searchQuery)
    }

    suspend fun addNote(note: Note) {
            noteDao.insert(note)
    }

    fun updateNote(note: Note) {
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.update(note)
        }
    }
    fun deleteNote(note: Note) {
        coroutineScope.launch(Dispatchers.IO) {
            noteDao.delete(note)
        }
    }

    fun getNoteById(id: Int): Flow<Note?> {
        return noteDao.getNoteById(id)
    }
}