package com.luisdev.marknotes.core.di

import com.luisdev.marknotes.core.network.createHttpClient
import org.koin.dsl.module

val appModule = module {
    single { createHttpClient() }
//    single { ApiService(get()) }
//    single { MainViewModel(get()) }
}