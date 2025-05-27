package com.luisdev.marknotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.luisdev.marknotes.core.utils.UrlOpenerAndroid
import com.luisdev.marknotes.core.utils.initMarkdownContext
import com.luisdev.marknotes.features.login.LoginViewModel
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        initMarkdownContext(this)

        Napier.base(DebugAntilog())

        val urlOpener = UrlOpenerAndroid(this)
        val homeViewModel = LoginViewModel(urlOpener)

        setContent {
            App(homeViewModel)
        }
    }
}