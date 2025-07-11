package com.example.extra_notes.presentation.notes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.extra_notes.presentation.notes.viewmodel.NoteViewModel

@Composable
fun SearchBox(noteVM: NoteViewModel = viewModel()) {
    val searchQuery by noteVM.searchQuery.collectAsState()
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    var isEnabled by remember { mutableStateOf(true) }
    var hasFocus by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
            .background(Color.Transparent, MaterialTheme.shapes.extraLarge)
            .border(3.dp, Color.LightGray, MaterialTheme.shapes.extraLarge)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    if (!isEnabled) {
                        isEnabled = true
                    }
                    focusRequester.requestFocus()
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    hasFocus = focusState.isFocused
                    if (!focusState.isFocused && searchQuery.isNotEmpty() && isEnabled) {
                    }
                },
            value = searchQuery,
            onValueChange = {
                noteVM.updateSearchQuery(it)
                if (!isEnabled) isEnabled = true
            },
            label = { Text("Search...") },
            singleLine = true,
            enabled = isEnabled,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    if (searchQuery.isNotEmpty()) {
                        isEnabled = false
                    }
                }
            )
        )
    }
}
