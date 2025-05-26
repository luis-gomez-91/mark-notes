package com.luisdev.marknotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.compose.AppTheme
import com.luisdev.marknotes.core.navigation.NavigationWrapper
import com.luisdev.marknotes.core.navigation.NavigationWrapperDesktop
import com.luisdev.marknotes.core.utils.Platform
import com.luisdev.marknotes.features.settings.SettingsViewModel

@Composable
fun App() {
    val settingsViewModel = remember { SettingsViewModel() }
    val themeSelect by settingsViewModel.themeSelect.collectAsState()

    AppTheme (
        themeSelect
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            when(Platform.isDesktop()) {
                true -> NavigationWrapperDesktop()
                else -> NavigationWrapper(
                    settingsViewModel = settingsViewModel
                )
            }
        }
    }
}