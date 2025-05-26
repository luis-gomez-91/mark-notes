package com.luisdev.marknotes.core.utils

import platform.Foundation.NSBundle

actual fun getAppVersion(): String =
    NSBundle.mainBundle.infoDictionary?.get("CFBundleShortVersionString")?.toString() ?: "Unknown"

actual fun getBuildNumber(): String =
    NSBundle.mainBundle.infoDictionary?.get("CFBundleVersion")?.toString() ?: "Unknown"
