package com.luisdev.marknotes

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.luisdev.marknotes.core.utils.UrlOpenerAndroid
import com.luisdev.marknotes.core.utils.initMarkdownContext
import com.luisdev.marknotes.features.login.LoginViewModel
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.ExternalAuthAction
import io.github.jan.supabase.auth.FlowType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.handleDeeplinks
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.logging.LogLevel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var supabase: SupabaseClient

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        initMarkdownContext(this)
        Napier.base(DebugAntilog()) // Initialize Napier logging
        Napier.i("LOG LEVEL: ${LogLevel.DEBUG}", tag = "AppInit")

        // Initialize Supabase client
        supabase = createSupabaseClient(
            supabaseUrl = "https://wfojlrhaxxgxcfoimhgj.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Indmb2pscmhheHhneGNmb2ltaGdqIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc0OTA3NDg3MSwiZXhwIjoyMDY0NjUwODcxfQ.dbpHQJ4NLyYChlqt5oDvRdWVJnNyw_PfB3zqtgVbFMA"
        ) {
            defaultLogLevel = LogLevel.DEBUG
            install(Auth) {
                scheme = "marknotesapp"
                host = "callback"
                flowType = FlowType.PKCE
                alwaysAutoRefresh = true
                autoLoadFromStorage = true
                defaultExternalAuthAction = ExternalAuthAction.CustomTabs()
            }
        }

        val urlOpener = UrlOpenerAndroid(this)
        loginViewModel = LoginViewModel(urlOpener, supabase)

        lifecycleScope.launch {
            Napier.w("onCreate: Intent received: ${intent.action}, Data: ${intent.data}", tag = "DeeplinkHandler")
            supabase.auth.awaitInitialization()
            Napier.w("onCreate: Supabase Auth initialized.", tag = "DeeplinkHandler")
            handleIncomingIntent(intent)
        }

        setContent {
            App(loginViewModel)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Napier.w("onNewIntent: New Intent received: ${intent.action}, Data: ${intent.data}", tag = "DeeplinkHandler")
        lifecycleScope.launch {
            supabase.auth.awaitInitialization()
            handleIncomingIntent(intent)
        }
    }

    private fun handleIncomingIntent(intent: Intent) {
        Napier.w("handleIncomingIntent: Processing Intent: ${intent.action}, Data: ${intent.data}", tag = "DeeplinkHandler")
        val isAuthDeeplink = intent.data?.scheme == "marknotesapp" && intent.data?.host == "callback"

        if (isAuthDeeplink) {
            Napier.w("handleIncomingIntent: Auth deep link detected.", tag = "DeeplinkHandler")
            try {
                supabase.handleDeeplinks(intent)
                Napier.w("handleIncomingIntent: handleDeeplinks processed for authentication. Session status will be handled by ViewModel.", tag = "DeeplinkHandler")
            } catch (e: Exception) {
                Napier.e("handleIncomingIntent: Error handling deep link: ${e.message}", e, tag = "DeeplinkHandler")
            }
        } else {
            Napier.w("handleIncomingIntent: Not an auth deep link. ViewModel's sessionStatus will handle existing session check.", tag = "DeeplinkHandler")
        }
    }
}