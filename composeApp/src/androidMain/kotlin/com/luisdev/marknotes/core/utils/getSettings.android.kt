package com.luisdev.marknotes.core.utils

import android.content.Context
import com.russhwolf.settings.Settings

import com.russhwolf.settings.SharedPreferencesSettings

private lateinit var context: Context

fun initSettingsContext(ctx: Context) {
    context = ctx.applicationContext
}

actual fun getSettings(): Settings {
    val delegate = context.getSharedPreferences("MySettings", Context.MODE_PRIVATE)
    return SharedPreferencesSettings(delegate)
}