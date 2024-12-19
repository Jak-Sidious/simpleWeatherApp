package com.id.weatherapp.presentation.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = {
            onQueryChange(it)
            onSearch(it)
        },
        placeholder = { Text("Search Location") },
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.fillMaxWidth()
    )
}