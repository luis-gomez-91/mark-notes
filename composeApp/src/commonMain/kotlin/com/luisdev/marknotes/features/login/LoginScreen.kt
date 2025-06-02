package com.luisdev.marknotes.features.login


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.luisdev.marknotes.core.ui.components.MyDashboardBack
import io.github.aakira.napier.Napier
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.createSupabaseClient

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navHostController: NavHostController
) {
    MyDashboardBack(
        title = "",
        onBackClick = { navHostController.popBackStack() },
        content = {
            Screen(
                loginViewModel
            )
        }
    )
}

@Composable
fun Screen(
    loginViewModel: LoginViewModel
) {

    val supabase = createSupabaseClient(
        supabaseUrl = "https://wfojlrhaxxgxcfoimhgj.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Indmb2pscmhheHhneGNmb2ltaGdqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDgyMzM5ODYsImV4cCI6MjA2MzgwOTk4Nn0.Ku7drr3ymZM-veo-vhtKXUCq-tmJSrO1msVfLZYHFhg"
    ) {
        install(Auth) {
            scheme = "marknotesapp" // this can be anything, eg. your package name or app/company url (not your Supabase url)
            host = "callback"
        }
    }

    LaunchedEffect(Unit) {
        supabase.auth.signInWith(
            provider = Google,
            redirectUrl = "marknotesapp://callback"
        )

        val session = supabase.auth.sessionManager.loadSession()

        Napier.i( "$session", tag = "prueba")
        }
}
