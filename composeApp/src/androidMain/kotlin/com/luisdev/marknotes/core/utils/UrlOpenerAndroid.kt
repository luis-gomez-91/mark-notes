package com.luisdev.marknotes.core.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

class UrlOpenerAndroid(private val context: Context) : UrlOpener {
    override fun openURL(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Error al intentar abrir la URL: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

