package com.luisdev.marknotes.core.utils


private const val KEY_LANGUAGE = "language"
private const val KEY_THEME = "theme"

object AppSettings {
    private val settings by lazy { getSettings() }

    fun setLanguage(languageIso: String) {
        settings.putString(KEY_LANGUAGE, languageIso)
    }

    fun getLanguage(): String {
        return settings.getString(KEY_LANGUAGE, defaultValue = "en")
    }

    fun setTheme(theme: Theme) {
        settings.putString(KEY_THEME, theme.name)
    }

    fun getTheme(): Theme {
        val value = settings.getString(KEY_THEME, defaultValue = Theme.SystemDefault.name)
        return Theme.entries.firstOrNull { it.name == value } ?: Theme.SystemDefault
    }
}