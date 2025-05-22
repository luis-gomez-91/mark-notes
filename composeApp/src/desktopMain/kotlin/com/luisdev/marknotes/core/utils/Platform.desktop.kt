package com.luisdev.marknotes.core.utils

actual object Platform {
    actual fun isDesktop() = true
    actual fun isAndroid() = false
    actual fun isIos() = false
}