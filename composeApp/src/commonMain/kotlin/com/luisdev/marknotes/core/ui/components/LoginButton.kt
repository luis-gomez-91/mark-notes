package com.luisdev.marknotes.core.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage

@Composable
fun LoginButton(
    text: String,
    enabled: Boolean = true,
    onClickAction: () -> Unit,
    icon: ImageVector? = null,
    buttonColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.primary,
    iconSize: Dp = 24.dp,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(24.dp),
    urlImage: String? = null
) {
    FilledTonalButton(
        onClick = onClickAction,
        enabled = enabled,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = buttonColor,
            contentColor = if (enabled) textColor else MaterialTheme.colorScheme.outline
        ),
        modifier = modifier,
        shape = shape
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = if (enabled) textColor else MaterialTheme.colorScheme.outline,
            )
        } else if (urlImage != null) {
            AsyncImage(
                model = urlImage,
                contentDescription = null,
                modifier = Modifier.size(iconSize)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = textStyle,
            color = if (enabled) textColor else MaterialTheme.colorScheme.outline
        )
    }
}