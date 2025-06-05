package com.luisdev.marknotes.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisdev.marknotes.core.network.createHttpClient
import com.luisdev.marknotes.core.utils.AppSettings
import com.luisdev.marknotes.core.utils.UrlOpener
import com.luisdev.marknotes.domain.model.LoginProvider
import io.github.aakira.napier.Napier
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.user.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val urlOpener: UrlOpener,
    private val supabase: SupabaseClient
): ViewModel() {
    val client = createHttpClient()

    private val _session = MutableStateFlow<UserSession?>(null)
    val session: StateFlow<UserSession?> = _session

    private val _userId = MutableStateFlow<String?>(null)
    val userId: StateFlow<String?> = _userId

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun signInWithOAuth(
        provider: LoginProvider
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                supabase.auth.signInWith(
                    provider = Google,
                    redirectUrl = "marknotesapp://callback"
                ) {
                    scopes.addAll(listOf("email", "profile"))
                    queryParams["prompt"] = "select_account"
                }
//                when (provider) {
//                    is LoginProvider.IDToken -> {
//                        supabase.auth.signInWith(
//                            provider = provider.provider,
//                            redirectUrl = "marknotesapp://callback"
//                        ) {
//                            scopes.addAll(listOf("email", "profile"))
//                            queryParams["prompt"] = "select_account"
//                        }
//                    }
//                    is LoginProvider.OAuth -> {
//                        supabase.auth.signInWith(
//                            provider = provider.provider,
//                            redirectUrl = "marknotesapp://callback"
//                        ) {
//                            scopes.addAll(listOf("email", "profile"))
//                            queryParams["prompt"] = "select_account"
//                        }
//                    }
//                }

//                val loadedSession = supabase.auth.sessionManager.loadSession()
//                val accessToken = loadedSession?.accessToken
//                _session.value = loadedSession
//                loadedSession?.let {
//                    AppSettings.setSessionToken(it.accessToken)
//                    it.user?.let { user ->
//                        AppSettings.setUserId(user.id)
//                        _userId.value = user.id
//                    }
//                }
//
//                Napier.i("$accessToken", tag = "prueba")
//                Napier.i("${loadedSession?.user?.id}", tag = "prueba")
                Napier.i("TERMINO", tag = "caca")

            } catch (e: Exception) {
                _session.value = null
                Napier.i("ERROR POSI: $e", tag = "prueba")
            } finally {
//                _isLoading.value = false
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            try {
                val result = supabase.auth.signOut()
                AppSettings.clearSession()
                AppSettings.clearUserId()
                _session.value = null
                Napier.i("Sesión cerrada correctamente: $result", tag = "prueba")
            } catch (e: Exception) {
                Napier.e("Error al cerrar sesión: $e", tag = "prueba")
            }
        }
    }

    fun onSessionRestored(session: UserSession) {
        _session.value = session
        AppSettings.setSessionToken(session.accessToken)
        session.user?.let { user ->
            AppSettings.setUserId(user.id)
            _userId.value = user.id
        }
    }
}