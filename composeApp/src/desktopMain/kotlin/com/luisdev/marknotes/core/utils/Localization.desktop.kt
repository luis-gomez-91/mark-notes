package com.luisdev.marknotes.core.utils

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Localization {
    actual fun applyLanguage(iso: String) {
        println("Idioma cambiado a: $iso (desktop)")
    }
}