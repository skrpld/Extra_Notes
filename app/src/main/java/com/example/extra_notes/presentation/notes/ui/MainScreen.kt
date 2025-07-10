package com.example.extra_notes.presentation.notes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.extra_notes.presentation.notes.viewmodel.NoteViewModel

@Composable
fun MainScreen(navController: NavController, noteVM: NoteViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        SearchBox()

        NotesList(navController, noteVM)

        FloatingActionButton(
            onClick = { navController.navigate("NoteEditor") },
            modifier = Modifier
                .size(90.dp)
                .background(Color.Transparent, MaterialTheme.shapes.extraLarge)
                .absolutePadding(right = 25.dp, bottom = 25.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Note"
            )
        }
    }
}
