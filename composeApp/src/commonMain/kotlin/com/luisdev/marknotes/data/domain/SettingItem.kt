package com.luisdev.marknotes.data.domain

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingItem (
    val description: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)