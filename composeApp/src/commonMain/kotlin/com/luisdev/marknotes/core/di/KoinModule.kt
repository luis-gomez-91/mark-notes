package com.luisdev.marknotes.core.di

import com.luisdev.marknotes.core.network.createHttpClient
import com.luisdev.marknotes.features.settings.SettingsViewModel
import org.koin.dsl.module

val appModule = module {
    single { createHttpClient() }
    single { SettingsViewModel() }


//    single { ApiService(get()) }
//    single { MainViewModel(get()) }
}