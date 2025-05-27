package com.luisdev.marknotes.features.notes.screens.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.icons.CssGgIcons
import compose.icons.cssggicons.Notes
import compose.icons.cssggicons.PlayListCheck
import marknotes.composeapp.generated.resources.Res
import marknotes.composeapp.generated.resources.notes
import marknotes.composeapp.generated.resources.tasks
import org.jetbrains.compose.resources.stringResource

@Composable
fun DrawerTab(

) {
    var selectedTabIndex: Int by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState { 2 }


    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    TabRowPagoOnline(
        selectedTabIndex = selectedTabIndex,
        onTabSelected = { index ->
            selectedTabIndex = index
        }
    )

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        when(page) {
//                    0 -> data?.let { CardRubros(it.rubros, pagoOnlineViewModel, homeViewModel) }
//                    1 -> data?.let { CardDatosFacturacion(pagoOnlineViewModel) }
        }
    }
}

@Composable
fun TabRowPagoOnline(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceContainer),
        indicator = { tabPositions ->
            SecondaryIndicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .height(3.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    ) {
        Tab(
            selected = selectedTabIndex == 1,
            onClick = {
                onTabSelected(0)
            },
            text = {
                Text(
                    text = stringResource(Res.string.notes),
                    style = MaterialTheme.typography.labelSmall,
                    color = if (selectedTabIndex == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            },
            icon = {
                Icon(
                    imageVector = CssGgIcons.Notes,
                    contentDescription = stringResource(Res.string.notes),
                    tint = if (selectedTabIndex == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier.size(20.dp)
                )
            }
        )

        Tab(
            selected = selectedTabIndex == 0,
            onClick = {
                onTabSelected(1)
            },
            text = {
                Text(
                    text = stringResource(Res.string.tasks),
                    style = MaterialTheme.typography.labelSmall,
                    color = if (selectedTabIndex == 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            },
            icon = {
                Icon(
                    imageVector = CssGgIcons.PlayListCheck,
                    contentDescription = stringResource(Res.string.tasks),
                    tint = if (selectedTabIndex == 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier.size(20.dp)
                )
            }
        )
    }
}