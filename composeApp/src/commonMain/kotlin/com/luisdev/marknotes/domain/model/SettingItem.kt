package com.luisdev.marknotes.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingItem (
    val title: String,
    val description: String? = null,
    val icon: ImageVector,
    val onClick: (() -> Unit)? = null
)