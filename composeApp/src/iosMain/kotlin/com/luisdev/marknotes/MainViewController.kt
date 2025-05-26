package com.luisdev.marknotes

import androidx.compose.ui.window.ComposeUIViewController
import com.luisdev.marknotes.core.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure =  { initializeKoin()}
) { App() }