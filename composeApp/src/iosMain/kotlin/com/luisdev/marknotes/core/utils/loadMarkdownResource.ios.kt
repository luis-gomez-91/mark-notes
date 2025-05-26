package com.luisdev.marknotes.core.utils

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import platform.Foundation.*

@OptIn(ExperimentalForeignApi::class)
actual suspend fun loadMarkdownResource(fileKey: String, langCode: String): String {
    val filename = "${fileKey}_${langCode}"
    return withContext(Dispatchers.Default) {
        val path = NSBundle.mainBundle.pathForResource(filename, "md")
        if (path != null) {
            NSString.stringWithContentsOfFile(path, NSUTF8StringEncoding, null) as String
        } else {
            "Archivo no encontrado: $filename.md"
        }
    }
}