package com.luisdev.marknotes.core.utils

expect object Platform {
    fun isDesktop(): Boolean
    fun isAndroid(): Boolean
    fun isIos(): Boolean
}