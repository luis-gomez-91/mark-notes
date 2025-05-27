package com.luisdev.marknotes

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.luisdev.marknotes.core.utils.UrlOpenerDesktop
import com.luisdev.marknotes.features.login.LoginViewModel

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MarkNotes",
    ) {

        val urlOpener = UrlOpenerDesktop()
        val loginViewModel = LoginViewModel(urlOpener)

        Window(onCloseRequest = ::exitApplication, title = "MarkNotes") {
            App(loginViewModel)
        }

//        App()
    }
}