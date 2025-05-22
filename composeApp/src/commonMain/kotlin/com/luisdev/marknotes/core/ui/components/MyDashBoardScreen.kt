package com.luisdev.marknotes.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MyDashBoardScreen(
    title: String,
    navController: NavHostController,
    content: @Composable () -> Unit = {},
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MyDrawerContent(
                drawerState,
                navController
            )
        },
        content = {
            Scaffold(
                topBar = { MyTopBar(title = title, drawerState = drawerState) },
                bottomBar = { MyBottomBar(navController = navController) }
            ) { innerPadding ->
                Surface(
                    Modifier.padding(innerPadding)
                ) {
//                    Column (
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(bottom = 4.dp)
//                    ) {
                        content()
//                    }
                }
            }

        }
    )
}
