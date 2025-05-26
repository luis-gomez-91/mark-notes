package com.luisdev.marknotes.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luisdev.marknotes.features.home.HomeScreen
import com.luisdev.marknotes.features.login.LoginScreen
import com.luisdev.marknotes.features.settings.AppVersionScreen
import com.luisdev.marknotes.features.settings.PrivacyPolicyScreen
import com.luisdev.marknotes.features.settings.SettingsScreen
import com.luisdev.marknotes.features.settings.SettingsViewModel
import com.luisdev.marknotes.features.settings.TermsConditionsScreen

@Composable
fun NavigationWrapper(
    settingsViewModel: SettingsViewModel
) {
    val navController = rememberNavController()
    val language = settingsViewModel.languageSelect.collectAsState()

    NavHost(navController = navController, startDestination = Home) {

        composable<Login> { LoginScreen() }
        composable<Home> { HomeScreen(navController, settingsViewModel) }
        composable<Settings> { SettingsScreen(navController, settingsViewModel) }
        composable<TermsCondition> { TermsConditionsScreen(language = language.value, onBackClick = { navController.popBackStack() }) }
        composable<PrivacyPolicy> { PrivacyPolicyScreen(language = language.value, onBackClick = { navController.popBackStack() }) }
        composable<AppVersion> { AppVersionScreen(language = language.value, onBackClick = { navController.popBackStack() }) }
    }
}