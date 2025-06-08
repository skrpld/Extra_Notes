package com.example.extra_notes

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.extra_notes.presentation.notes.ui.MainScreen
import com.example.extra_notes.presentation.notes.ui.theme.Extra_NotesTheme
import com.example.extra_notes.presentation.notes.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    class NoteViewModelFactory(val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NoteViewModel(application) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Extra_NotesTheme {
                val owner = LocalViewModelStoreOwner.current

                owner?.let {
                    val viewModel: NoteViewModel = viewModel(
                        it,
                        "NoteViewModel",
                        NoteViewModelFactory(LocalContext.current.applicationContext as Application)
                    )
                    MainScreen(viewModel)
                }
            }
        }
    }
}
