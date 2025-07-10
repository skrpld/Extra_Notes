package com.example.extra_notes.presentation.notes.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.extra_notes.presentation.notes.viewmodel.NoteViewModel
import com.example.extra_notes.data.local.db.Note

@SuppressLint("UnrememberedMutableState")
@Composable
fun NoteEditor(
    id: Int = 0,
    navController: NavController,
    noteVM: NoteViewModel = viewModel()
) {
    val note by if (id != 0) {
        noteVM.getNoteById(id).collectAsState(initial = null)
    } else {
        remember { mutableStateOf(Note(0, "", "", false)) }
    }

    var title by remember { mutableStateOf(note?.title ?: "") }
    var content by remember { mutableStateOf(note?.content ?: "") }
    var isPinned by remember { mutableStateOf(note?.isPinned ?: false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            maxLines = 5
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { isPinned = !isPinned }
        ) {
            Checkbox(
                checked = isPinned,
                onCheckedChange = { isPinned = it }
            )
            Text(
                text = "Pin this note",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { navController.popBackStack() }) {
                Text("Cancel")
            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        noteVM.addNote(title, content, isPinned)
                        navController.popBackStack()
                    }
                },
                enabled = title.isNotBlank()
            ) {
                Text("Save")
            }
        }
    }
}