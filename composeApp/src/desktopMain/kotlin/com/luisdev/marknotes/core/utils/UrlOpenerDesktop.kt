package com.luisdev.marknotes.core.utils

import java.awt.Desktop
import java.net.URI

class UrlOpenerDesktop : UrlOpener {
    override fun openURL(url: String) {
        try {
            if (Desktop.isDesktopSupported()) {
                val desktop = Desktop.getDesktop()
                desktop.browse(URI(url))
            } else {
                println("Desktop no soportado en esta plataforma.")
            }
        } catch (e: Exception) {
            println("Error al abrir URL: ${e.message}")
        }
    }
}