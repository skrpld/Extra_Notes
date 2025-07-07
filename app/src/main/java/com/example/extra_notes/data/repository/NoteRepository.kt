package com.example.extra_notes.data.repository

import com.example.extra_notes.data.local.db.Note
import com.example.extra_notes.data.local.db.NoteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteRepository(
    private val noteDao: NoteDao
) {
    val noteList: Flow<List<Note>> = noteDao.getAllNotes()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

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

    fun getNoteById(id: Int): Note? = noteDao.getNoteById(id)

    fun getNotes(): Flow<List<Note>> = noteDao.getAllNotes()
}