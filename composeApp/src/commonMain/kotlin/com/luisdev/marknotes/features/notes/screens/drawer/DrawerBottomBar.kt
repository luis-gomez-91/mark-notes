package com.luisdev.marknotes.features.notes.screens.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.LocalOffer
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.outlined.UnfoldMore
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.luisdev.marknotes.domain.model.BottomBarItem
import com.luisdev.marknotes.features.notes.NotesViewModel
import compose.icons.CssGgIcons
import compose.icons.cssggicons.FileAdd
import compose.icons.cssggicons.FolderAdd
import marknotes.composeapp.generated.resources.Res
import marknotes.composeapp.generated.resources.add
import marknotes.composeapp.generated.resources.back
import marknotes.composeapp.generated.resources.forward
import marknotes.composeapp.generated.resources.notes
import org.jetbrains.compose.resources.stringResource

@Composable
fun DrawerBottomBar(
    notesViewModel: NotesViewModel
) {
    val items = listOf(
        BottomBarItem(
            icon = CssGgIcons.FileAdd,
            description = stringResource(Res.string.back),
            onClick = { notesViewModel.createNote() }
        ),
        BottomBarItem(
            icon = CssGgIcons.FolderAdd,
            description = stringResource(Res.string.forward),
            onClick = { /* Acción para adelante */ }
        ),
        BottomBarItem(
            icon = Icons.Outlined.FilterList,
            description = stringResource(Res.string.notes),
            onClick = { /* Acción para agregar */ }
        ),
        BottomBarItem(
            icon = Icons.Outlined.UnfoldMore,
            description = stringResource(Res.string.add),
            onClick = { /* Acción para agregar */ }
        ),
        BottomBarItem(
            icon = Icons.Outlined.LocalOffer,
            description = stringResource(Res.string.add),
            onClick = { /* Acción para agregar */ }
        ),
        BottomBarItem(
            icon = Icons.Outlined.StarOutline,
            description = stringResource(Res.string.add),
            onClick = { /* Acción para agregar */ }
        )
    )

    BottomAppBar(
        containerColor = Color.Transparent,
        tonalElevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items.forEach { item ->
                IconButton(
                    onClick = { item.onClick() }
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.description,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}