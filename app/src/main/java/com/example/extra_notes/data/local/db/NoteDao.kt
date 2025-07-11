package com.example.extra_notes.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM notes_database WHERE id = :id")
    fun getNoteById(id: Int): Flow<Note?>

    @Query("SELECT * FROM notes_database WHERE title LIKE '%' || :searchQuery || '%' OR content LIKE '%' || :searchQuery || '%'")
    fun getAllNotes(searchQuery: String): Flow<List<Note>>
}