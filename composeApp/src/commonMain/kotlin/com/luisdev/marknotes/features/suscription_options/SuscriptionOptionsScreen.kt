package com.luisdev.marknotes.features.suscription_options

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.luisdev.marknotes.core.ui.components.MyDashboardBack
import marknotes.composeapp.generated.resources.Res
import marknotes.composeapp.generated.resources.premium_plans
import org.jetbrains.compose.resources.stringResource

@Composable
fun SuscriptionOptionsScreen(
    onBackClick: () -> Unit
) {
    MyDashboardBack(
        title = stringResource(Res.string.premium_plans),
        onBackClick = { onBackClick() },
        content = {
            Screen(

            )
        }
    )
}

@Composable
fun Screen(

) {
    Text("HOLA")
}