package com.luisdev.marknotes.core.ui.components

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MyHorizontalDivider(

) {
    HorizontalDivider(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f))
}