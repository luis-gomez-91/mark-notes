package com.luisdev.marknotes

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.luisdev.marknotes.core.utils.AppSettings
import com.luisdev.marknotes.core.utils.UrlOpenerAndroid
import com.luisdev.marknotes.core.utils.initMarkdownContext
import com.luisdev.marknotes.features.login.LoginViewModel
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.handleDeeplinks
import io.github.jan.supabase.createSupabaseClient
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var loginViewModel: LoginViewModel
    val supabase = createSupabaseClient(
        supabaseUrl = "https://wfojlrhaxxgxcfoimhgj.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Indmb2pscmhheHhneGNmb2ltaGdqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDgyMzM5ODYsImV4cCI6MjA2MzgwOTk4Nn0.Ku7drr3ymZM-veo-vhtKXUCq-tmJSrO1msVfLZYHFhg"
    ) {
        install(Auth) {
            scheme = "marknotesapp"
            host = "callback"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

//        supabase.handleDeeplinks(intent)
        initMarkdownContext(this)
        Napier.base(DebugAntilog())

        val urlOpener = UrlOpenerAndroid(this)
        loginViewModel = LoginViewModel(urlOpener, supabase)
//        loginViewModel.restoreSessionIfPossible()

        lifecycleScope.launch {
            Napier.i("Procesando intent con handleDeeplinks...", tag = "deeplink")
            supabase.handleDeeplinks(intent)
            Napier.i("Intent procesado. Restaurando sesión...", tag = "deeplink")
            loginViewModel.restoreSessionIfPossible()
        }

        setContent {
            App(loginViewModel)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Napier.i("🚨 onNewIntent recibido con intent.data=${intent.data}", tag = "deeplink")
        lifecycleScope.launch {
            Napier.i("Procesando nuevo intent con handleDeeplinks...", tag = "deeplink")
            supabase.handleDeeplinks(intent)
            Napier.i("Nuevo intent procesado. Restaurando sesión...", tag = "deeplink")
            loginViewModel.restoreSessionIfPossible()
        }
    }

}
