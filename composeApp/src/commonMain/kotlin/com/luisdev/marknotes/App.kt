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
import com.luisdev.marknotes.features.login.LoginViewModel
import com.luisdev.marknotes.features.notes.NotesViewModel
import com.luisdev.marknotes.features.settings.SettingsViewModel

@Composable
fun App(
    loginViewModel: LoginViewModel
) {
    val settingsViewModel = remember { SettingsViewModel() }
    val notesViewModel = remember { NotesViewModel() }
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
                true -> NavigationWrapperDesktop(
                    loginViewModel = loginViewModel,
                    settingsViewModel = settingsViewModel,
                    notesViewModel = notesViewModel
                )
                else -> NavigationWrapper(
                    loginViewModel = loginViewModel,
                    settingsViewModel = settingsViewModel,
                    notesViewModel = notesViewModel
                )
            }
        }
    }
}