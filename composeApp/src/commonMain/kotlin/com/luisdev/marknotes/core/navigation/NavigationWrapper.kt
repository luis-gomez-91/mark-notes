package com.luisdev.marknotes.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luisdev.marknotes.features.login.LoginScreen
import com.luisdev.marknotes.features.login.LoginViewModel
import com.luisdev.marknotes.features.notes.NotesViewModel
import com.luisdev.marknotes.features.notes.screens.NotesScreen
import com.luisdev.marknotes.features.settings.AppVersionScreen
import com.luisdev.marknotes.features.settings.PrivacyPolicyScreen
import com.luisdev.marknotes.features.settings.SettingsScreen
import com.luisdev.marknotes.features.settings.SettingsViewModel
import com.luisdev.marknotes.features.settings.TermsConditionsScreen

@Composable
fun NavigationWrapper(
    loginViewModel: LoginViewModel,
    settingsViewModel: SettingsViewModel,
    notesViewModel: NotesViewModel
) {
    val navController = rememberNavController()
    val language = settingsViewModel.languageSelect.collectAsState()

    NavHost(navController = navController, startDestination = Notes) {

        composable<Login> { LoginScreen(loginViewModel, navController) }
        composable<Notes> { NotesScreen(navController, settingsViewModel, notesViewModel) }
        composable<Settings> { SettingsScreen(navController, settingsViewModel, loginViewModel) }
        composable<TermsCondition> { TermsConditionsScreen(language = language.value, onBackClick = { navController.popBackStack() }) }
        composable<PrivacyPolicy> { PrivacyPolicyScreen(language = language.value, onBackClick = { navController.popBackStack() }) }
        composable<AppVersion> { AppVersionScreen(language = language.value, onBackClick = { navController.popBackStack() }) }

    }
}