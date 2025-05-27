package com.luisdev.marknotes

import androidx.compose.ui.window.ComposeUIViewController
import com.luisdev.marknotes.core.di.initializeKoin
import com.luisdev.marknotes.features.login.LoginViewModel
import com.luisdev.marknotes.core.utils.UrlOpenerIOS

fun MainViewController() = ComposeUIViewController(
    configure =  { initializeKoin()}
) {
    val urlOpener = UrlOpenerIOS()
    val loginViewModel = LoginViewModel(urlOpener)
    App(loginViewModel)
}