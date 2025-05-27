package com.luisdev.marknotes.features.notes.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.luisdev.marknotes.features.notes.NotesViewModel
import com.luisdev.marknotes.features.notes.screens.drawer.MainDrawer

@Composable
fun MainScaffold(
    title: String,
    navController: NavHostController,
    notesViewModel: NotesViewModel,
    content: @Composable () -> Unit = {},
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MainDrawer(
                notesViewModel = notesViewModel
            )
        },
        content = {
            Scaffold(
                topBar = { MainTopBar(title = title, drawerState = drawerState, navHostController = navController) },
                bottomBar = { MainBottomBar(navController = navController) }
            ) { innerPadding ->
                Surface(
                    Modifier.padding(innerPadding)
                ) {
                        content()
                }
            }

        }
    )
}
