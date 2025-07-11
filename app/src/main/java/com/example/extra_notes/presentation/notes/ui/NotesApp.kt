package com.example.extra_notes.presentation.notes.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.extra_notes.presentation.notes.viewmodel.NoteViewModel

@Composable
fun NotesApp(noteVM: NoteViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "MainScreen") {
        composable("MainScreen") { MainScreen(navController) }
        composable(
            route = "NoteEditor?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { stackEntry ->
            val noteId = stackEntry.arguments?.getInt("id") ?: 0
            NoteEditor(noteId, navController)
        }
    }
}