package com.luisdev.marknotes.features.notes

import androidx.lifecycle.ViewModel
import com.luisdev.marknotes.core.network.createHttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NotesViewModel: ViewModel() {
    val client = createHttpClient()
//    val service = AluFinanzasService(client)

    private val _showSearch = MutableStateFlow(false)
    val showSearch: StateFlow<Boolean> = _showSearch

    fun setShowSearch(newValue: Boolean) {
        _showSearch.value = newValue
    }
}