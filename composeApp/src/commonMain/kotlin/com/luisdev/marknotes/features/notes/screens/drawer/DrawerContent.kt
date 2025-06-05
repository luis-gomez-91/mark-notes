package com.luisdev.marknotes.features.notes.screens.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import com.luisdev.marknotes.core.ui.components.LoginButton
import com.luisdev.marknotes.core.ui.components.MyFilledTonalButton
import com.luisdev.marknotes.features.notes.NotesViewModel

@Composable
fun DrawerContent(
    notesViewModel: NotesViewModel
) {
    val showSearch by notesViewModel.showSearch.collectAsState(false)
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        notesViewModel.fetchNotes()
    }

    ModalDrawerSheet(
        modifier = Modifier
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Scaffold(
                topBar = {
                    DrawerTopBar(
                        focusRequester = focusRequester,
                        showSearch = showSearch,
                        onShowSearch = { notesViewModel.setShowSearch(!showSearch) }
                    )
                },
                bottomBar = {
                    DrawerBottomBar(
                        notesViewModel = notesViewModel
                    )
                }
            ) { innerPadding ->
                Surface(
                    Modifier.padding(innerPadding)
                ) {
                    MyFilledTonalButton(
                        text = "notas",
                        onClickAction = {
                            notesViewModel.fetchNotes()
                        }
                    )
                }
            }

        }
    }
}