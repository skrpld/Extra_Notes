package com.example.extra_notes.presentation.notes.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.extra_notes.presentation.notes.viewmodel.NoteViewModel

@Composable
fun NotesApp(noteVM: NoteViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "MainScreen") {
        composable("MainScreen") { MainScreen(noteVM) }
        composable("NoteEditor") { NoteEditor(0) }
    }
}