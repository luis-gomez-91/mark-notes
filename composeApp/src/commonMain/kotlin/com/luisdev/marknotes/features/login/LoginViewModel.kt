package com.luisdev.marknotes.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil3.Uri
import com.luisdev.marknotes.core.network.createHttpClient
import com.luisdev.marknotes.core.utils.AppSettings
import com.luisdev.marknotes.core.utils.URL_SERVER
import com.luisdev.marknotes.core.utils.UrlOpener
import com.luisdev.marknotes.data.domain.LoginProvider
import io.github.aakira.napier.Napier
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.SignOutScope
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.status.SessionSource
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.createSupabaseClient
import io.ktor.client.request.get
import io.ktor.http.headers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val urlOpener: UrlOpener,
    private val supabase: SupabaseClient
): ViewModel() {
    val client = createHttpClient()
//    val service = AluFinanzasService(client)

    private val _session = MutableStateFlow<UserSession?>(supabase.auth.currentSessionOrNull())
    val session: StateFlow<UserSession?> = _session

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun signInWithOAuth(
        provider: LoginProvider
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                when (provider) {
                    is LoginProvider.IDToken -> {
                        supabase.auth.signInWith(
                            provider = provider.provider,
                            redirectUrl = "marknotesapp://callback"
                        ) {
                            scopes.addAll(listOf("email", "profile"))
                            queryParams["prompt"] = "select_account"
                        }
                    }
                    is LoginProvider.OAuth -> {
                        supabase.auth.signInWith(
                            provider = provider.provider,
                            redirectUrl = "marknotesapp://callback"
                        ) {
                            scopes.addAll(listOf("email", "profile"))
                            queryParams["prompt"] = "select_account"
                        }
                    }
                }
                val loadedSession = supabase.auth.sessionManager.loadSession()
                val accessToken = loadedSession?.accessToken
                _session.value = loadedSession
                loadedSession?.let { AppSettings.setSessionToken(it.accessToken) }

                Napier.i("${accessToken}", tag = "prueba")

//                val response = client.get("${URL_SERVER}notas") {
//                    headers {
//                        append("Authorization", "Bearer $accessToken")
//                    }
//                }

            } catch (e: Exception) {
                _session.value = null
                Napier.i("ERROR POSI: $e")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            try {
                val result = supabase.auth.signOut()
                AppSettings.clearSession()
                _session.value = null
                Napier.i("Sesión cerrada correctamente: $result", tag = "prueba")
            } catch (e: Exception) {
                Napier.e("Error al cerrar sesión: $e", tag = "prueba")
            }
        }
    }

    fun restoreSessionIfPossible() {
        viewModelScope.launch {
            val token = AppSettings.getSessionToken()
            if (token != null) {
                try {
                    val restoredSession = supabase.auth.sessionManager.loadSession()
                    _session.value = restoredSession
                    Napier.i("Sesión restaurada correctamente", tag = "prueba")
                } catch (e: Exception) {
                    AppSettings.clearSession()
                    _session.value = null
                    Napier.e("Error restaurando sesión: $e", tag = "prueba")
                }
            }
        }
    }


}