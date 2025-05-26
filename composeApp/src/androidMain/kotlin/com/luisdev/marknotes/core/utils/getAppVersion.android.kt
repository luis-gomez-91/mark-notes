package com.luisdev.marknotes.core.utils

import android.content.Context
import android.content.pm.PackageManager

lateinit var androidAppContext: Context

actual fun getAppVersion(): String {
    return try {
        val packageInfo = androidAppContext
            .packageManager
            .getPackageInfo(androidAppContext.packageName, 0)
        packageInfo.versionName ?: "Unknown"
    } catch (e: PackageManager.NameNotFoundException) {
        "Unknown"
    }
}

actual fun getBuildNumber(): String {
    return try {
        val packageInfo = androidAppContext
            .packageManager
            .getPackageInfo(androidAppContext.packageName, 0)
        packageInfo.versionCode.toString()
    } catch (e: PackageManager.NameNotFoundException) {
        "Unknown"
    }
}
