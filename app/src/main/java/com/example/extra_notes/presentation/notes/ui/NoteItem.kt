package com.example.extra_notes.presentation.notes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.extra_notes.data.local.db.Note

@Composable
fun NoteItem(note: Note) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.primary, MaterialTheme.shapes.extraLarge),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
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
    }
}