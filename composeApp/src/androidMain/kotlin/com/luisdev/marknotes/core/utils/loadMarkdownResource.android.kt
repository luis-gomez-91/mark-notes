package com.luisdev.marknotes.core.utils

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

lateinit var appContext: Context

fun initMarkdownContext(context: Context) {
    appContext = context
}

actual suspend fun loadMarkdownResource(fileKey: String, langCode: String): String {
    val filename = "${fileKey}_${langCode}.md"
    return withContext(Dispatchers.IO) {
        try {
            appContext.assets.open(filename).bufferedReader().use { it.readText() }
//            appContext.assets.open("terms_en.md").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            "Archivo no encontrado: $filename"
        }
    }
}