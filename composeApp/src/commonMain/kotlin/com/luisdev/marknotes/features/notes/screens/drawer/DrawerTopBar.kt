package com.luisdev.marknotes.features.notes.screens.drawer

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import compose.icons.CssGgIcons
import compose.icons.cssggicons.Close
import compose.icons.cssggicons.Search
import marknotes.composeapp.generated.resources.Res
import marknotes.composeapp.generated.resources.find_note
import marknotes.composeapp.generated.resources.search
import marknotes.composeapp.generated.resources.slogan
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerTopBar(
    focusRequester: FocusRequester,
    showSearch: Boolean,
    onShowSearch: () -> Unit
) {
    TopAppBar(
        title = {
            if (showSearch) {
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
                TextField(
                    value = "",
                    onValueChange = { },
                    textStyle = MaterialTheme.typography.labelMedium,
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(Res.string.find_note),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search,
                        keyboardType = KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            focusRequester.freeFocus()
                        }
                    )
                )
            } else {
                Text(
                    text = stringResource(Res.string.slogan),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface
        ),
        actions = {
            AnimatedContent(targetState = showSearch) { isSearch ->
                IconButton(
                    onClick = {
                        onShowSearch()
                    }
                ) {
                    Icon(
                        imageVector = if (isSearch) CssGgIcons.Close else CssGgIcons.Search,
                        contentDescription = stringResource(Res.string.search),
                        tint = if (isSearch) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    )
}