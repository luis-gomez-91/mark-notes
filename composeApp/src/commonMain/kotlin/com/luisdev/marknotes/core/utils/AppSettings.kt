package com.luisdev.marknotes.core.utils

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

    // Manejo de sesión
    fun setSessionToken(token: String) {
        settings.putString(KEY_SESSION_TOKEN, token)
    }

    fun getSessionToken(): String? {
        return settings.getStringOrNull(KEY_SESSION_TOKEN)
    }

    fun clearSession() {
        settings.remove(KEY_SESSION_TOKEN)
    }

}