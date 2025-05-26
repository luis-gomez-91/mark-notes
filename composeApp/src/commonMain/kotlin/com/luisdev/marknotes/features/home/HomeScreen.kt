package com.luisdev.marknotes.features.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.luisdev.marknotes.core.ui.components.MyDashBoardScreen
import com.luisdev.marknotes.core.utils.Language
import com.luisdev.marknotes.features.settings.SettingsViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    settingsViewModel: SettingsViewModel
) {

    val currentLanguageIso by settingsViewModel.languageSelect.collectAsState()
    val currentLanguage = Language.entries.first { it.iso == currentLanguageIso }

    MyDashBoardScreen(
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