package com.luisdev.marknotes.core.utils

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual fun getSettings(): Settings {
    val delegate: NSUserDefaults = NSUserDefaults.standardUserDefaults
    val settings: Settings = NSUserDefaultsSettings(delegate)
    return settings
}