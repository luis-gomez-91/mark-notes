package com.luisdev.marknotes.core.utils

import com.luisdev.marknotes.MR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc


expect object Platform {
    fun isDesktop(): Boolean
    fun isAndroid(): Boolean
    fun isIos(): Boolean
}