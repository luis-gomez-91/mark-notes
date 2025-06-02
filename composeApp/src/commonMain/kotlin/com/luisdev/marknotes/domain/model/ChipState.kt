package com.luisdev.marknotes.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class ChipState(
    val containerColor: Color,
    val labelColor: Color,
    val icon: ImageVector,
    val label: String
)