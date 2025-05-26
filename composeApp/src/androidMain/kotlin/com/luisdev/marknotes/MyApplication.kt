package com.luisdev.marknotes

import android.app.Application
import com.luisdev.marknotes.core.di.initializeKoin
import com.luisdev.marknotes.core.utils.AppSettings
import com.luisdev.marknotes.core.utils.Localization
import com.luisdev.marknotes.core.utils.androidAppContext
import com.luisdev.marknotes.core.utils.initSettingsContext
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initSettingsContext(this)
        androidAppContext = applicationContext

        val languageIso = AppSettings.getLanguage()
        Localization(this).applyLanguage(languageIso)

        initializeKoin(
            config = { androidContext(this@MyApplication) }
        )
    }
}