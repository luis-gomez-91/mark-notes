package com.luisdev.marknotes.core.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import compose.icons.CssGgIcons
import marknotes.composeapp.generated.resources.Res
import marknotes.composeapp.generated.resources.notes
import org.jetbrains.compose.resources.stringResource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import compose.icons.cssggicons.ChevronLeft

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDashboardBack(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit,
    content: @Composable () -> Unit = {},
) {

    ModalNavigationDrawer(
        drawerContent = {  },
        content = {
            Scaffold(
                topBar = {
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

                        navigationIcon = {
                            IconButton(
                                onClick = { onBackClick() }
                            ) {
                                Icon(
                                    imageVector = CssGgIcons.ChevronLeft,
                                    contentDescription = stringResource(Res.string.notes),
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    )
                },
            ) { innerPadding ->
                Surface(
                    modifier.padding(innerPadding)
                ) {
                    content()
                }
            }
        }
    )
}
