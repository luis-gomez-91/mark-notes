package com.luisdev.marknotes.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisdev.marknotes.core.network.createHttpClient // Asegúrate que esto crea un HttpClient ya configurado
import com.luisdev.marknotes.core.utils.AppSettings
import com.luisdev.marknotes.core.utils.URL_SERVER // Asegúrate que esta URL es correcta
import com.luisdev.marknotes.core.utils.UrlOpener
import com.luisdev.marknotes.data.remote.response.BaseResponse
import com.luisdev.marknotes.data.remote.response.LoginResponse
import com.luisdev.marknotes.domain.model.LoginProvider
import io.github.aakira.napier.Napier
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.user.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import io.github.jan.supabase.auth.status.SessionStatus
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class LoginViewModel(
    private val urlOpener: UrlOpener,
    private val supabase: SupabaseClient,
    // Si no, asegúrate que createHttpClient() es idempontente
    // y solo crea el cliente una vez globalmente.
): ViewModel() {

    val httpClient = createHttpClient()
    // Si createHttpClient() NO es idempotente, deberías inyectar el HttpClient
    // así: private val httpClient: HttpClient

    private val _session = MutableStateFlow<UserSession?>(null)
    val session: StateFlow<UserSession?> = _session

    private val _userId = MutableStateFlow<String?>(null)
    val userId: StateFlow<String?> = _userId

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            supabase.auth.sessionStatus.collect { status ->
                when (status) {
                    is SessionStatus.Authenticated -> {
                        val supabaseAccessToken = status.session.accessToken

                        try {
                            _isLoading.value = true
                            val loginResponseKtor = httpClient.post("${URL_SERVER}login") {
                                contentType(ContentType.Application.Json)
                                setBody(mapOf("supabase_access_token" to supabaseAccessToken))
                            }

                            // *** AJUSTE IMPORTANTE AQUÍ: LoginApiData, no LoginResponse ***
                            val responseBody = loginResponseKtor.body<BaseResponse<LoginResponse>>() // Debería ser LoginApiData

                            if (responseBody.status == "success" && responseBody.data != null) {
                                val apiAccessToken = responseBody.data.access_token

                                AppSettings.setSessionToken(apiAccessToken)
                                _session.value = status.session
                                // Asegúrate de que user_id no sea nulo antes de usarlo
                                responseBody.data.user_id?.let { // Agregué el '?' para seguridad
                                    AppSettings.setUserId(it)
                                    _userId.value = it
                                }
                                Napier.i("LoginViewModel: Token de API personalizado obtenido y guardado. User ID: ${responseBody.data.user_id}", tag = "AuthFlow")

                            } else if (responseBody.status == "error" && responseBody.error != null) {
                                Napier.e("LoginViewModel: Error lógico del backend: ${responseBody.error.message} (Código: ${responseBody.error.code})", tag = "AuthFlow")
                                _session.value = null
                                _userId.value = null
                                AppSettings.clearSessionToken()
                            } else {
                                Napier.e("LoginViewModel: Respuesta inesperada del backend: $responseBody", tag = "AuthFlow")
                                _session.value = null
                                _userId.value = null
                                AppSettings.clearSessionToken()
                            }
                        } catch (e: ClientRequestException) {
                            val errorResponse = try { e.response.body<BaseResponse<Unit>>() } catch (parseEx: Exception) { null }
                            Napier.e("LoginViewModel: Error de cliente (HTTP ${e.response.status.value}): ${errorResponse?.error?.message ?: e.message}", tag = "AuthFlow", throwable = e)
                            _session.value = null
                            _userId.value = null
                            AppSettings.clearSessionToken()
                        } catch (e: ServerResponseException) {
                            val errorResponse = try { e.response.body<BaseResponse<Unit>>() } catch (parseEx: Exception) { null }
                            Napier.e("LoginViewModel: Error de servidor (HTTP ${e.response.status.value}): ${errorResponse?.error?.message ?: e.message}", tag = "AuthFlow", throwable = e)
                            _session.value = null
                            _userId.value = null
                            AppSettings.clearSessionToken()
                        } catch (e: Exception) {
                            Napier.e("LoginViewModel: Excepción inesperada al llamar a /login: ${e.message}", tag = "AuthFlow", throwable = e)
                            _session.value = null
                            _userId.value = null
                            AppSettings.clearSessionToken()
                        } finally {
                            _isLoading.value = false
                        }
                    }
                    is SessionStatus.Initializing -> {
                        Napier.i("LoginViewModel: Cargando sesión desde almacenamiento / inicializando.", tag = "AuthFlow")
                        _isLoading.value = true
                    }
                    is SessionStatus.RefreshFailure -> {
                        Napier.e("LoginViewModel: Error al refrescar la sesión: ${status.cause}", tag = "AuthFlow")
                        _session.value = null
                        _userId.value = null
                        _isLoading.value = false
                        AppSettings.clearSessionToken()
                    }
                    is SessionStatus.NotAuthenticated -> {
                        Napier.i("LoginViewModel: No autenticado. SignOut: ${status.isSignOut}", tag = "AuthFlow")
                        _session.value = null
                        _userId.value = null
                        AppSettings.clearSessionToken()
                    }
                }
            }
        }
    }

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
            } catch (e: Exception) {
                _session.value = null
                Napier.i("ERROR POSI: $e", tag = "prueba")
            } finally {
                // _isLoading.value = false // El estado Authenticated o NotAuthenticated se encargará de esto
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            try {
                val result = supabase.auth.signOut()
                AppSettings.clearSession() // Asumo que esto limpia tu token de API también
                AppSettings.clearUserId()
                _session.value = null
                _userId.value = null
                Napier.i("Sesión cerrada correctamente: $result", tag = "prueba")
            } catch (e: Exception) {
                Napier.e("Error al cerrar sesión: $e", tag = "prueba")
            }
        }
    }
}