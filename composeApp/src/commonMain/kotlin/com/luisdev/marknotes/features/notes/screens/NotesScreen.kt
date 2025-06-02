package com.luisdev.marknotes.features.notes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.luisdev.marknotes.core.utils.Language
import com.luisdev.marknotes.features.notes.NotesViewModel
import com.luisdev.marknotes.features.notes.screens.main.MainScaffold
import com.luisdev.marknotes.features.settings.SettingsViewModel
import com.multiplatform.webview.web.LoadingState
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewState
import kotlinx.coroutines.delay

@Composable
fun NotesScreen(
    navController: NavHostController,
    settingsViewModel: SettingsViewModel,
    notesViewModel: NotesViewModel
) {

    val currentLanguageIso by settingsViewModel.languageSelect.collectAsState()
    val currentLanguage = Language.entries.first { it.iso == currentLanguageIso }

    MainScaffold(
        notesViewModel = notesViewModel,
        title = "Nota 1",
        navController = navController,
        content = {
            Screen(
                notesViewModel = notesViewModel
            )
        }
    )

}

@Composable
fun Screen(
    notesViewModel: NotesViewModel
) {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        val markdown = """
            # Hola desde Android
            Este contenido viene desde Kotlin.
        """.trimIndent()
            .replace("\"", "\\\"")
            .replace("\n", "\\n")

        val webViewState = rememberWebViewState(url = "file:///android_asset/web_editor/index.html")
        val navigator = rememberWebViewNavigator()
        val markdownText by notesViewModel.markdownText.collectAsState(null)

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = "Markdown exportado:\n$markdownText"
        )

        WebView(
            state = webViewState,
            modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
            navigator = navigator,
        )

        LaunchedEffect(webViewState.loadingState) {
            if (webViewState.loadingState !is LoadingState.Loading) {
                navigator.evaluateJavaScript("window.setMarkdownContent(\"$markdown\")") { result ->
//                                logInfo("prueba", "RESULT: $result")
                }
            }
        }

        LaunchedEffect(Unit) {
            while (true) {
                delay(2000) // Espera 2 segundos
                navigator.evaluateJavaScript("window.getMarkdown()") { result ->
                    val cleanedResult = result?.removeSurrounding("\"")?.replace("\\n", "\n") ?: ""
                    notesViewModel.setMarkdownText(cleanedResult)
                }
            }
        }
    }

}