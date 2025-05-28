package com.luisdev.marknotes.features.notes.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
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
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("HOLA MUNDO")
    }

}