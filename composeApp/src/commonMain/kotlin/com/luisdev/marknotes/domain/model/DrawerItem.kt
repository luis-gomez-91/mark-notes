package com.luisdev.marknotes.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerItem(
    val label: String,
    val icon: ImageVector,
    val onclick: () -> Unit
)