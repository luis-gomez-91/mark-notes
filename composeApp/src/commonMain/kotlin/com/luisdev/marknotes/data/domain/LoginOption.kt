package com.luisdev.marknotes.data.domain

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.jan.supabase.auth.providers.IDTokenProvider
import io.github.jan.supabase.auth.providers.OAuthProvider

data class LoginOption (
    val name: String,
    val provider: LoginProvider,
    val icon: ImageVector?,
    val urlImage: String?,
    val colorContainer: Color,
    val colorText: Color
)

sealed class LoginProvider {
    data class IDToken(val provider: IDTokenProvider) : LoginProvider()
    data class OAuth(val provider: OAuthProvider) : LoginProvider()
}