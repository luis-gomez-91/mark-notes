package com.luisdev.marknotes.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarItem(
    val icon: ImageVector,
    val description: String,
    val onClick: () -> Unit
)