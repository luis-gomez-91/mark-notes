package com.luisdev.marknotes.data.domain

import androidx.compose.foundation.lazy.grid.GridItemSpan

data class SettingGroup (
    val title: String,
    val settingItems: List<SettingItem>
)