package com.luisdev.marknotes.core.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

actual suspend fun loadMarkdownResource(fileKey: String, langCode: String): String {
    val filename = "${fileKey}_${langCode}.md"
    return withContext(Dispatchers.IO) {
        object {}.javaClass.classLoader.getResource(filename)?.readText()
            ?: "Archivo no encontrado: $filename"
    }
}