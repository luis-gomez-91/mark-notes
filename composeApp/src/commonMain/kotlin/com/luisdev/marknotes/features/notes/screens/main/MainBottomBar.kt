package com.luisdev.marknotes.features.notes.screens.main

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.luisdev.marknotes.data.domain.BottomBarItem
import compose.icons.CssGgIcons
import compose.icons.cssggicons.AddR
import compose.icons.cssggicons.ChevronLeft
import compose.icons.cssggicons.ChevronRight
import compose.icons.cssggicons.Notes
import compose.icons.cssggicons.PlayListCheck
import marknotes.composeapp.generated.resources.Res
import marknotes.composeapp.generated.resources.add
import marknotes.composeapp.generated.resources.back
import marknotes.composeapp.generated.resources.forward
import marknotes.composeapp.generated.resources.notes
import marknotes.composeapp.generated.resources.tasks
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainBottomBar(
    navController: NavHostController
) {
    val items = listOf(
        BottomBarItem(
            icon = CssGgIcons.ChevronLeft,
            description = stringResource(Res.string.back),
            onClick = { /* Acción para atrás */ }
        ),
        BottomBarItem(
            icon = CssGgIcons.ChevronRight,
            description = stringResource(Res.string.forward),
            onClick = { /* Acción para adelante */ }
        ),
        BottomBarItem(
            icon = CssGgIcons.AddR,
            description = stringResource(Res.string.add),
            onClick = { /* Acción para agregar */ }
        ),
        BottomBarItem(
            icon = CssGgIcons.Notes,
            description = stringResource(Res.string.notes),
            onClick = { /* Acción para agregar */ }
        ),
        BottomBarItem(
            icon = CssGgIcons.PlayListCheck,
            description = stringResource(Res.string.tasks),
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
                val interactionSource = remember { MutableInteractionSource() }
                val isPressed by interactionSource.collectIsPressedAsState()
                val scale by animateFloatAsState(
                    targetValue = if (isPressed) 0.90f else 1f,
                    animationSpec = tween(durationMillis = 150),
                    label = "scaleAnimation"
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(MaterialTheme.shapes.extraLarge)
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale
                        )
                        .background(
//                            if (isPressed) MaterialTheme.colorScheme.secondary.copy(alpha = 0.05f)
                            Color.Transparent
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