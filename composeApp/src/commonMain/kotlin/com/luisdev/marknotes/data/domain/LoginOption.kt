package com.luisdev.marknotes.data.domain

import androidx.compose.ui.graphics.vector.ImageVector
import io.github.jan.supabase.auth.providers.IDTokenProvider

data class LoginOption (
    val name: String,
    val provider: IDTokenProvider,
    val icon: ImageVector
)