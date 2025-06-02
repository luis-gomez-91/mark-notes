package com.luisdev.marknotes.features.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisdev.marknotes.core.network.createHttpClient
import com.luisdev.marknotes.core.utils.AppSettings
import com.luisdev.marknotes.data.remote.request.NoteRequest
import com.luisdev.marknotes.data.remote.service.NotesService
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotesViewModel: ViewModel() {
    val client = createHttpClient()
    val service = NotesService(client)

    private val _showSearch = MutableStateFlow(false)
    val showSearch: StateFlow<Boolean> = _showSearch

    fun setShowSearch(newValue: Boolean) {
        _showSearch.value = newValue
    }

    private val _markdownText = MutableStateFlow<String?>(null)
    val markdownText: StateFlow<String?> = _markdownText

    fun setMarkdownText(markdown: String) {
        _markdownText.value = markdown
    }

    fun createNote() {
        viewModelScope.launch {
            AppSettings.getUserId()?.let { user ->
                val noteRequest = NoteRequest(
                    userId = user,
                    languageIso = AppSettings.getLanguage()
                )
                val response = AppSettings.getSessionToken()?.let {
                    service.createNote(
                        body = noteRequest,
                        token = it
                    )
                }
                Napier.i("$response", tag = "prueba")
            }
        }
    }

    fun fetchNotes() {
        viewModelScope.launch {
            Napier.i("ENTRO AL FETCH", tag = "prueba")
            val response = AppSettings.getSessionToken()?.let {
                service.fetchNotes(token = it)
            }
            Napier.i("$response", tag = "prueba")

        }
    }
}