package com.luisdev.marknotes.features.notes.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.luisdev.marknotes.core.utils.Language
import com.luisdev.marknotes.features.notes.NotesViewModel
import com.luisdev.marknotes.features.notes.screens.main.MainScaffold
import com.luisdev.marknotes.features.settings.SettingsViewModel

@Composable
fun NotesScreen(
    navController: NavHostController,
    settingsViewModel: SettingsViewModel,
    notesViewModel: NotesViewModel
) {

    val currentLanguageIso by settingsViewModel.languageSelect.collectAsState()
    val currentLanguage = Language.entries.first { it.iso == currentLanguageIso }

    MainScaffold(
        notesViewModel = notesViewModel,
        title = "Nota 1",
        navController = navController,
        content = {
            Screen()
        }
    )

}

@Composable
fun Screen(

) {

}