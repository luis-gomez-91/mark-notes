package com.luisdev.marknotes.features.notes.screens.drawer

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.luisdev.marknotes.core.navigation.Settings
import com.luisdev.marknotes.core.ui.components.MyOutlinedTextField
import com.luisdev.marknotes.features.notes.NotesViewModel
import com.luisdev.marknotes.features.notes.screens.main.MainBottomBar
import com.luisdev.marknotes.features.notes.screens.main.MainTopBar
import compose.icons.CssGgIcons
import compose.icons.cssggicons.AddR
import compose.icons.cssggicons.Close
import compose.icons.cssggicons.MenuRightAlt
import compose.icons.cssggicons.Notes
import compose.icons.cssggicons.Options
import compose.icons.cssggicons.PlayListCheck
import compose.icons.cssggicons.Search
import compose.icons.cssggicons.Stark
import compose.icons.cssggicons.Tag
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch
import marknotes.composeapp.generated.resources.Res
import marknotes.composeapp.generated.resources.notes
import marknotes.composeapp.generated.resources.search
import marknotes.composeapp.generated.resources.settings
import marknotes.composeapp.generated.resources.starred
import marknotes.composeapp.generated.resources.tags
import marknotes.composeapp.generated.resources.tasks
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDrawer(
    notesViewModel: NotesViewModel
) {
    val showSearch by notesViewModel.showSearch.collectAsState(false)

//    DropdownMenu(
//        expanded = true,
//        onDismissRequest = {},
//    ) {
//        DropdownMenuItem(
//            text = { Text("prueba 1") },
//            leadingIcon = {
//                Icon(
//                    imageVector = CssGgIcons.Notes,
//                    contentDescription = stringResource(Res.string.notes),
//                    tint = if (selectedTabIndex == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
//                    modifier = Modifier.size(20.dp)
//                )
//            },
//            onClick = {}
//        )
//    }

    val focusRequester = remember { FocusRequester() }

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
                    DrawerBottomBar()
                }
            ) { innerPadding ->
                Surface(
                    Modifier.padding(innerPadding)
                ) {


//                    DrawerTab()
                }
            }

        }
    }
}