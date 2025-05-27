package com.luisdev.marknotes.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisdev.marknotes.core.network.createHttpClient
import com.luisdev.marknotes.core.utils.AppSettings
import com.luisdev.marknotes.core.utils.UrlOpener
import io.github.aakira.napier.Napier
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.IDTokenProvider
import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.createSupabaseClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.headers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val urlOpener: UrlOpener
): ViewModel() {
    val client = createHttpClient()
//    val service = AluFinanzasService(client)

    private val _session = MutableStateFlow<UserSession?>(null)
    val session: StateFlow<UserSession?> = _session

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val supabase = createSupabaseClient(
        supabaseUrl = "https://wfojlrhaxxgxcfoimhgj.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Indmb2pscmhheHhneGNmb2ltaGdqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDgyMzM5ODYsImV4cCI6MjA2MzgwOTk4Nn0.Ku7drr3ymZM-veo-vhtKXUCq-tmJSrO1msVfLZYHFhg"
    ) {
        install(Auth) {
            scheme = "marknotesapp"
            host = "callback"
        }
    }

    fun signInWithOAuth(
        provider: IDTokenProvider
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                supabase.auth.signInWith(
                    provider = provider,
                    redirectUrl = "marknotesapp://callback"
                )
                val loadedSession = supabase.auth.sessionManager.loadSession()
                Napier.i("${loadedSession}", tag = "prueba")
                _session.value = loadedSession
            } catch (e: Exception) {
                _session.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getAccessToken(): String? {
        return _session.value?.accessToken
    }
}