package com.example.extra_notes.presentation.notes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchBox() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color.Transparent, MaterialTheme.shapes.extraLarge)
            .border(3.dp, Color.LightGray, MaterialTheme.shapes.extraLarge),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            modifier = Modifier.size(60.dp)
        )
        Text(
            text = "My Notes",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}