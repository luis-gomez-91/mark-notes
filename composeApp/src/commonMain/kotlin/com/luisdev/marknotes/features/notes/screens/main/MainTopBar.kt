package com.luisdev.marknotes.features.notes.screens.main

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.luisdev.marknotes.core.navigation.Settings
import compose.icons.CssGgIcons
import compose.icons.cssggicons.MenuRightAlt
import compose.icons.cssggicons.Options
import kotlinx.coroutines.launch
import marknotes.composeapp.generated.resources.Res
import marknotes.composeapp.generated.resources.notes
import marknotes.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    title: String,
    drawerState: DrawerState,
    navHostController: NavHostController
) {
    val scope = rememberCoroutineScope()

    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface
        ),
        actions = {
            IconButton(
                onClick = {
                    navHostController.navigate(Settings)
                }
            ) {
                Icon(
                    imageVector = CssGgIcons.Options,
                    contentDescription = stringResource(Res.string.settings),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { scope.launch { drawerState.open() } }
            ) {
                Icon(
                    imageVector = CssGgIcons.MenuRightAlt,
                    contentDescription = stringResource(Res.string.notes),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    )
}

