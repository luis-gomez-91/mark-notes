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
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var supabase: SupabaseClient // Declararla aquí para que sea accesible

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        initMarkdownContext(this)
        Napier.base(DebugAntilog())

        Napier.i("LOG LEVEL: ${LogLevel.DEBUG}", tag = "deeplink")

        // Inicializa Supabase aquí, antes de cualquier otra cosa que dependa de ella
        supabase = createSupabaseClient(
            supabaseUrl = "https://wfojlrhaxxgxcfoimhgj.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Indmb2pscmhheHhneGNmb2ltaGdqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDgyMzM5ODYsImV4cCI6MjA2MzgwOTk4Nn0.Ku7drr3ymZM-veo-vhtKXUCq-tmJSrO1msVfLZYHFhg"
//            logger = NapierLogger(LogLevel.DEBUG)
        ) {
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
            Napier.w("onCreate: Recibido Intent: ${intent.action}, Data: ${intent.data}", tag = "deeplink")

            // Siempre espera la inicialización temprano en onCreate para asegurar la carga de la sesión
            supabase.auth.awaitInitialization()
            Napier.w("onCreate: Supabase Auth inicializado.", tag = "deeplink")

            // Maneja el intent que lanzó la actividad (que podría ser un deep link)
            // Esto es crucial para inicios en frío donde onCreate es el primer punto de entrada
            handleIncomingIntent(intent)
        }

        setContent {
            App(loginViewModel)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Napier.w("onNewIntent: ESTE ES EL NEW INTENT: ${intent.action}, Data: ${intent.data}", tag = "deeplink")
        // Asegúrate de que Supabase Auth esté inicializado antes de manejar nuevos intents
        lifecycleScope.launch {
            supabase.auth.awaitInitialization() // Importante también para nuevos intents
            handleIncomingIntent(intent)
        }
    }

    private suspend fun handleIncomingIntent(intent: Intent) {
        Napier.w("handleIncomingIntent: Procesando Intent: ${intent.action}, Data: ${intent.data}", tag = "deeplink")

        val isAuthDeeplink = intent.data?.scheme == "marknotesapp" && intent.data?.host == "callback"

        if (isAuthDeeplink) {
            Napier.w("handleIncomingIntent: Deep Link de autenticación detectado.", tag = "deeplink")
            try {
                // Aquí el SDK de Supabase va a hacer una llamada de red.
                // Los logs de esa llamada DEBERÍAN aparecer aquí si la app no se cierra.
                supabase.handleDeeplinks(intent)
                Napier.w("handleIncomingIntent: handleDeeplinks PROCESADO PARA AUTENTICACION.", tag = "deeplink")

                val session = supabase.auth.currentSessionOrNull()
                Napier.w("handleIncomingIntent: Sesión después de handleDeeplinks: $session", tag = "deeplink")

                if (session == null) {
                    Napier.w("handleIncomingIntent: Sesión es NULL, no hay sesión después de handleDeeplinks.", tag = "deeplink")
                    // !!! Estas líneas deben estar COMENTADAS o ELIMINADAS TEMPORALMENTE !!!
                    // kotlinx.coroutines.delay(1000)
                    // val restoredSession = supabase.auth.currentSessionOrNull()
                    // if (restoredSession != null) {
                    //    Napier.w("handleIncomingIntent: Sesión encontrada después del retraso: $restoredSession", tag = "deeplink")
                    //    loginViewModel.onSessionRestored(restoredSession)
                    // } else {
                    //    Napier.w("handleIncomingIntent: Todavía no hay sesión después del retraso. Forzando reinicio.", tag = "deeplink")
                    //    restartApp()
                    // }
                    // !!! Asegúrate de que no haya NADA aquí que haga que la app se cierre o reinicie !!!
                } else {
                    Napier.w("handleIncomingIntent: Sesión obtenida correctamente de deeplink.", tag = "deeplink")
                    loginViewModel.onSessionRestored(session)
                }
            } catch (e: Exception) {
                Napier.e("handleIncomingIntent: Error al manejar el deep link: ${e.message}", e, tag = "deeplink")
            }
        } else {
            Napier.w("handleIncomingIntent: No es un deep link de autenticación. Comprobando la sesión existente.", tag = "deeplink")
            val session = supabase.auth.currentSessionOrNull()
            Napier.w("handleIncomingIntent: Sesión para intent no deep link: $session", tag = "deeplink")
            session?.let { loginViewModel.onSessionRestored(it) }
        }
    }

    private fun restartApp() {
        Napier.w("restartApp: Forzando reinicio de la aplicación.", tag = "deeplink")
        val packageManager = packageManager
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        val componentName = intent!!.component
        val mainIntent = Intent.makeRestartActivityTask(componentName)
        startActivity(mainIntent)
        finish() // Finaliza la actividad actual
        exitProcess(0) // Mata el proceso actual
    }
}