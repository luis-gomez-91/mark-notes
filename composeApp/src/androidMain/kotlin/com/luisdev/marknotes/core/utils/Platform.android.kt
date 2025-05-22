package com.luisdev.marknotes.core.utils

actual object Platform {
    actual fun isDesktop() = false
    actual fun isAndroid() = true
    actual fun isIos() = false
}