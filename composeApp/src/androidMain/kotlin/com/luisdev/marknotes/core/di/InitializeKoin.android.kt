package com.luisdev.marknotes.core.di

import com.luisdev.marknotes.core.utils.Localization
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val targetModule = module {
    single<Localization> { Localization(context = androidContext()) }
}