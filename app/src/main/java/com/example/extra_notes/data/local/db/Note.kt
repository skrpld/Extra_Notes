package com.example.extra_notes.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_database")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var content: String,
    var isPinned: Boolean,
)