package com.luisdev.marknotes.core.utils

import java.util.Properties

private val versionProperties by lazy {
    Properties().apply {
        val stream = this::class.java.getResourceAsStream("/version.properties")
        if (stream != null) load(stream)
    }
}

actual fun getAppVersion(): String = versionProperties.getProperty("app.version", "Unknown")
actual fun getBuildNumber(): String = versionProperties.getProperty("app.build", "Unknown")
