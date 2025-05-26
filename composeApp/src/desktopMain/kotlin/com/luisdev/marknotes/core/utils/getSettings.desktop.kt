package com.luisdev.marknotes.core.utils

import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

class DesktopSettings(private val prefs: Preferences) : Settings {

    override val keys: Set<String>
        get() = prefs.keys().toSet()

    override val size: Int
        get() = prefs.keys().size

    override fun clear() {
        prefs.clear()
    }

    override fun remove(key: String) {
        prefs.remove(key)
    }

    override fun hasKey(key: String): Boolean {
        return prefs.get(key, null) != null
    }

    override fun putInt(key: String, value: Int) {
        prefs.putInt(key, value)
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return prefs.getInt(key, defaultValue)
    }

    override fun getIntOrNull(key: String): Int? {
        return if (hasKey(key)) prefs.getInt(key, 0) else null
    }

    override fun putLong(key: String, value: Long) {
        prefs.putLong(key, value)
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return prefs.getLong(key, defaultValue)
    }

    override fun getLongOrNull(key: String): Long? {
        return if (hasKey(key)) prefs.getLong(key, 0L) else null
    }

    override fun putString(key: String, value: String) {
        prefs.put(key, value)
    }

    override fun getString(key: String, defaultValue: String): String {
        return prefs.get(key, defaultValue) ?: defaultValue
    }

    override fun getStringOrNull(key: String): String? {
        return prefs.get(key, null)
    }

    override fun putFloat(key: String, value: Float) {
        prefs.putFloat(key, value)
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        return prefs.getFloat(key, defaultValue)
    }

    override fun getFloatOrNull(key: String): Float? {
        return if (hasKey(key)) prefs.getFloat(key, 0f) else null
    }

    override fun putDouble(key: String, value: Double) {
        prefs.putDouble(key, value)
    }

    override fun getDouble(key: String, defaultValue: Double): Double {
        return prefs.getDouble(key, defaultValue)
    }

    override fun getDoubleOrNull(key: String): Double? {
        return if (hasKey(key)) prefs.getDouble(key, 0.0) else null
    }

    override fun putBoolean(key: String, value: Boolean) {
        prefs.putBoolean(key, value)
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    override fun getBooleanOrNull(key: String): Boolean? {
        return if (hasKey(key)) prefs.getBoolean(key, false) else null
    }
}

actual fun getSettings(): Settings {
    val prefs = Preferences.userRoot().node("AppSettings")
    return DesktopSettings(prefs)
}


