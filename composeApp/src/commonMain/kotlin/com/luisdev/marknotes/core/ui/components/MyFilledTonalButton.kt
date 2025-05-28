package com.luisdev.marknotes.core.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage

@Composable
fun MyFilledTonalButton(
    text: String,
    enabled: Boolean = true,
    onClickAction: () -> Unit,
    icon: ImageVector? = null,
    buttonColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.primary,
    iconSize: Dp = 24.dp,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(24.dp)
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
//            Icon(
//                imageVector = icon,
//                contentDescription = text,
//                modifier = Modifier.size(iconSize),
//                tint = if (enabled) textColor else MaterialTheme.colorScheme.outline
//            )
            AsyncImage(
                model = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Google_%22G%22_logo.svg/800px-Google_%22G%22_logo.svg.png",
                contentDescription = "Foto",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))

        }
        Text(
            text = text,
            style = textStyle,
            color = if (enabled) textColor else MaterialTheme.colorScheme.outline
        )
    }
}