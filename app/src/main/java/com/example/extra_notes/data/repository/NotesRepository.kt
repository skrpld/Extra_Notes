package com.example.extra_notes.data.repository

import com.example.extra_notes.data.local.db.Note
import com.example.extra_notes.data.local.db.NoteDao
import kotlinx.coroutines.flow.Flow

class NotesRepository(
    private val noteDao: NoteDao
) {
    fun addNote(note: Note): Long = noteDao.insert(note)

    fun updateNote(note: Note) = noteDao.update(note)

    fun deleteNote(note: Note) = noteDao.delete(note)

    fun getNoteById(id: Long): Note? = noteDao.getNoteById(id)

    fun setPinned(id: Long, isPinned: Boolean) = noteDao.setPinned(id, isPinned)

    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()
}