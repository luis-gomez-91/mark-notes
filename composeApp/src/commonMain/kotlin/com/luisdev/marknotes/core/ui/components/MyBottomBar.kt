package com.luisdev.marknotes.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.luisdev.marknotes.MR
import com.luisdev.marknotes.data.domain.BottomBarItem
import compose.icons.CssGgIcons
import compose.icons.cssggicons.AddR
import compose.icons.cssggicons.ChevronLeft
import compose.icons.cssggicons.ChevronRight
import compose.icons.cssggicons.Notes
import compose.icons.cssggicons.PlayListCheck
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun MyBottomBar(
    navController: NavHostController
) {
    val items = listOf(
        BottomBarItem(
            icon = CssGgIcons.ChevronLeft,
            description = stringResource(MR.strings.back),
            onClick = { /* Acción para atrás */ }
        ),
        BottomBarItem(
            icon = CssGgIcons.ChevronRight,
            description = stringResource(MR.strings.forward),
            onClick = { /* Acción para adelante */ }
        ),
        BottomBarItem(
            icon = CssGgIcons.AddR,
            description = stringResource(MR.strings.add),
            onClick = { /* Acción para agregar */ }
        ),
        BottomBarItem(
            icon = CssGgIcons.PlayListCheck,
            description = stringResource(MR.strings.tasks),
            onClick = { /* Acción para agregar */ }
        ),
        BottomBarItem(
            icon = CssGgIcons.Notes,
            description = stringResource(MR.strings.notes),
            onClick = { /* Acción para agregar */ }
        ),

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
                val interactionSource = remember { MutableInteractionSource() }
                val isPressed by interactionSource.collectIsPressedAsState()
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(
                            if (isPressed) MaterialTheme.colorScheme.secondary.copy(alpha = 0.05f)
                            else Color.Transparent
                        )
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = item.onClick
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.description,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}