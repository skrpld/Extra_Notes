package com.example.extra_notes.presentation.notes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.extra_notes.data.local.db.Note
import com.example.extra_notes.presentation.notes.viewmodel.NoteViewModel

@Composable
fun NoteItem(
    note: Note,
    noteVM: NoteViewModel = viewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.extraLarge),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            modifier = Modifier
                .size(width = 69.dp, height = 84.dp)
                .padding(start = 15.dp, top = 15.dp, bottom = 15.dp),
            checked = note.isPinned,
            onCheckedChange = { newValue ->
                val updatedNote = note.copy(isPinned = newValue)
                noteVM.updateNote(updatedNote)
            }
        )

        Column(
            modifier = Modifier
                .weight(1F)
                .fillMaxHeight()
                .padding(top = 10.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = note.title,
                fontSize = 20.sp,
                lineHeight = 22.sp,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = note.content,
                fontSize = 16.sp,
                lineHeight = 18.sp,
                maxLines = 2,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        IconButton(
            onClick = { noteVM.deleteNote(note) },
            modifier = Modifier
                .size(width = 69.dp, height = 84.dp)
                .padding(top = 15.dp, bottom = 15.dp, end = 15.dp)
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}