package com.example.extra_notes.data.repository

import com.example.extra_notes.data.local.db.Note
import com.example.extra_notes.data.local.db.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(
    private val noteDao: NoteDao
) {
    val noteList: Flow<List<Note>> = noteDao.getAllNotes()

    fun addNote(note: Note): Long = noteDao.insert(note)

    fun updateNote(note: Note) = noteDao.update(note)

    fun deleteNote(note: Note) = noteDao.delete(note)

    fun getNoteById(id: Int): Note? = noteDao.getNoteById(id)

    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()

    fun setPinned(id: Int, isPinned: Boolean) = noteDao.setPinned(id, isPinned)
}