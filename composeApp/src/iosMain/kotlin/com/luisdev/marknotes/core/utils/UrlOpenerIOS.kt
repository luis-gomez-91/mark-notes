package com.luisdev.marknotes.core.utils

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class UrlOpenerIOS : UrlOpener {
    override fun openURL(url: String) {
        val nsUrl = NSURL.URLWithString(url)
        if (nsUrl != null) {
            UIApplication.sharedApplication.openURL(
                nsUrl,
                options = emptyMap<Any?, Any>(),
                completionHandler = { success ->
                }
            )
        }
    }
}