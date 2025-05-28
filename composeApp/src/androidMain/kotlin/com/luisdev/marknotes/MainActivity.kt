package com.luisdev.marknotes

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.luisdev.marknotes.core.utils.AppSettings
import com.luisdev.marknotes.core.utils.UrlOpenerAndroid
import com.luisdev.marknotes.core.utils.initMarkdownContext
import com.luisdev.marknotes.features.login.LoginViewModel
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.handleDeeplinks
import io.github.jan.supabase.createSupabaseClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val supabase = createSupabaseClient(
            supabaseUrl = "https://wfojlrhaxxgxcfoimhgj.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Indmb2pscmhheHhneGNmb2ltaGdqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDgyMzM5ODYsImV4cCI6MjA2MzgwOTk4Nn0.Ku7drr3ymZM-veo-vhtKXUCq-tmJSrO1msVfLZYHFhg"
        ) {
            install(Auth) {
                scheme = "marknotesapp"
                host = "callback"
            }
        }

        supabase.handleDeeplinks(intent)
        initMarkdownContext(this)

        Napier.base(DebugAntilog())

        val urlOpener = UrlOpenerAndroid(this)
        val loginViewModel = LoginViewModel(urlOpener, supabase)
        loginViewModel.restoreSessionIfPossible()

        setContent {
            App(loginViewModel)
        }
    }
}
