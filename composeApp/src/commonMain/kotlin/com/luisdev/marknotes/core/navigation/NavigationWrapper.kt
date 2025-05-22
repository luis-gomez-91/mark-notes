package com.luisdev.marknotes.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luisdev.marknotes.features.home.HomeScreen
import com.luisdev.marknotes.features.login.LoginScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {

        composable<Login> { LoginScreen() }
        composable<Home> { HomeScreen(navController) }
    }
}