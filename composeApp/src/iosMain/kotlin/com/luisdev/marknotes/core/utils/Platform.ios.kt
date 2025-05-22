package com.luisdev.marknotes.core.utils

actual object Platform {
    actual fun isDesktop() = false
    actual fun isAndroid() = false
    actual fun isIos() = true
}