package com.example.androidproject.ui.components


import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: () -> Unit,
    onClearQuery: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = { newQuery ->
            onQueryChanged(newQuery)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = "Search icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                onClearQuery()
            }) {
                Icon(
                    imageVector = Icons.Rounded.Clear,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Clear text"
                )
            }
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            textColor = Color.Black
        ),
        placeholder = { Text(text = "Search deals") },
        textStyle = MaterialTheme.typography.headlineSmall,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    SearchView(
        query = "Test",
        onQueryChanged = { },
        onSearch = { },
        onClearQuery = { })
}