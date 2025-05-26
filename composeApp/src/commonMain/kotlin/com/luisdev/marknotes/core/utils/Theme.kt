package com.luisdev.marknotes.core.utils

import androidx.compose.runtime.Composable
import marknotes.composeapp.generated.resources.Res
import marknotes.composeapp.generated.resources.dark_theme
import marknotes.composeapp.generated.resources.light_theme
import marknotes.composeapp.generated.resources.system_theme
import org.jetbrains.compose.resources.stringResource

enum class Theme {
    Light,
    Dark,
    SystemDefault
}

@Composable
fun Theme.getThemeName() : String {
    return when (this) {
        Theme.Light -> stringResource(Res.string.light_theme)
        Theme.Dark -> stringResource(Res.string.dark_theme)
        Theme.SystemDefault -> stringResource(Res.string.system_theme)
    }
}
