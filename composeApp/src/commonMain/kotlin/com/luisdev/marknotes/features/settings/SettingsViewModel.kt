package com.luisdev.marknotes.features.settings

import androidx.lifecycle.ViewModel
import com.luisdev.marknotes.core.network.createHttpClient
import com.luisdev.marknotes.core.utils.AppSettings
import com.luisdev.marknotes.core.utils.Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class SettingsViewModel: ViewModel() {
    val client = createHttpClient()
//    val service = AluFinanzasService(client)

//    Language
    private val _languageSelect = MutableStateFlow(AppSettings.getLanguage())
    val languageSelect: StateFlow<String> = _languageSelect

    fun setLanguage(lang: String) {
        AppSettings.setLanguage(lang)
        _languageSelect.value = lang
    }

    private val _languageBottomSheet = MutableStateFlow(false)
    val languageBottomSheet: StateFlow<Boolean> = _languageBottomSheet

    fun setLanguageBottomSheet(newValue: Boolean) {
        _languageBottomSheet.value = newValue
    }

//    Theme
    private val _themeSelect = MutableStateFlow(AppSettings.getTheme())
    val themeSelect: StateFlow<Theme> = _themeSelect

    fun setTheme(newValue: Theme) {
        _themeSelect.value = newValue
        AppSettings.setTheme(newValue)
    }

    private val _themeBottomSheet = MutableStateFlow(false)
    val themeBottomSheet: StateFlow<Boolean> = _themeBottomSheet

    fun setThemeBottomSheet(newValue: Boolean) {
        _themeBottomSheet.value = newValue
    }
}