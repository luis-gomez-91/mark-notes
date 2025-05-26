package com.luisdev.marknotes.features.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.luisdev.marknotes.core.navigation.AppVersion
import com.luisdev.marknotes.core.navigation.PrivacyPolicy
import com.luisdev.marknotes.core.navigation.TermsCondition
import com.luisdev.marknotes.core.ui.components.MyCard
import com.luisdev.marknotes.core.ui.components.MyDashboardBack
import com.luisdev.marknotes.core.utils.Language
import com.luisdev.marknotes.core.utils.Localization
import com.luisdev.marknotes.core.utils.Theme
import com.luisdev.marknotes.core.utils.getThemeName
import com.luisdev.marknotes.data.domain.SettingGroup
import com.luisdev.marknotes.data.domain.SettingItem
import compose.icons.CssGgIcons
import compose.icons.cssggicons.ChevronRight
import compose.icons.cssggicons.EditContrast
import compose.icons.cssggicons.FileDocument
import compose.icons.cssggicons.GlobeAlt
import compose.icons.cssggicons.Info
import compose.icons.cssggicons.Key
import compose.icons.cssggicons.Lock
import compose.icons.cssggicons.LogIn
import marknotes.composeapp.generated.resources.Res
import marknotes.composeapp.generated.resources.account
import marknotes.composeapp.generated.resources.app
import marknotes.composeapp.generated.resources.app_version
import marknotes.composeapp.generated.resources.forward
import marknotes.composeapp.generated.resources.language
import marknotes.composeapp.generated.resources.not_active
import marknotes.composeapp.generated.resources.preferences
import marknotes.composeapp.generated.resources.privacy_policy
import marknotes.composeapp.generated.resources.select_language
import marknotes.composeapp.generated.resources.select_theme
import marknotes.composeapp.generated.resources.settings
import marknotes.composeapp.generated.resources.sign_in
import marknotes.composeapp.generated.resources.status
import marknotes.composeapp.generated.resources.subscribe_now
import marknotes.composeapp.generated.resources.subscription
import marknotes.composeapp.generated.resources.terms_conditions
import marknotes.composeapp.generated.resources.theme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun SettingsScreen(
    navHostController: NavHostController,
    settingsViewModel: SettingsViewModel,
) {
    MyDashboardBack(
        title = stringResource(Res.string.settings),
        onBackClick = { navHostController.popBackStack() },
        content = {
            Screen(
                navHostController, settingsViewModel
            )
        }
    )
}

@Composable
fun Screen(
    navHostController: NavHostController,
    settingsViewModel: SettingsViewModel,
) {
    val languageBottomSheet by settingsViewModel.languageBottomSheet.collectAsState(false)
    val themeBottomSheet by settingsViewModel.themeBottomSheet.collectAsState(false)

    val preferences = SettingGroup(
        title = stringResource(Res.string.preferences),
        settingItems = listOf(
            SettingItem(
                description = stringResource(Res.string.theme),
                icon = CssGgIcons.EditContrast,
                onClick = { settingsViewModel.setThemeBottomSheet(true) }
            ),
            SettingItem(
                description = stringResource(Res.string.language),
                icon = CssGgIcons.GlobeAlt,
                onClick = { settingsViewModel.setLanguageBottomSheet(true) }
            )
        )
    )

    val app = SettingGroup(
        title = stringResource(Res.string.app),
        settingItems = listOf(
            SettingItem(
                description = stringResource(Res.string.terms_conditions),
                icon = CssGgIcons.FileDocument,
                onClick = { navHostController.navigate(TermsCondition) }
            ),
            SettingItem(
                description = stringResource(Res.string.privacy_policy),
                icon = CssGgIcons.Lock,
                onClick = { navHostController.navigate(PrivacyPolicy) }
            ),
            SettingItem(
                description = stringResource(Res.string.app_version),
                icon = CssGgIcons.Info,
                onClick = { navHostController.navigate(AppVersion) }
            )
        )
    )

    val account = SettingGroup(
        title = stringResource(Res.string.account),
        settingItems = listOf(
            SettingItem(
                description = stringResource(Res.string.sign_in),
                icon = CssGgIcons.LogIn,
                onClick = {  }
            ),
        )
    )

    val suscription = SettingGroup(
        title = stringResource(Res.string.subscription),
        settingItems = listOf(
            SettingItem(
                description = "${stringResource(Res.string.status)}: ${stringResource(Res.string.not_active)}",
                icon = CssGgIcons.Info,
                onClick = {  }
            ),
            SettingItem(
                description = stringResource(Res.string.subscribe_now),
                icon = CssGgIcons.Key,
                onClick = {  }
            ),
        )
    )

    val settingGroups = listOf(account, suscription, preferences, app)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
//            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(settingGroups) { group ->
            SettingItem(group)
            Spacer(Modifier.height(8.dp))
        }
    }

    if (languageBottomSheet) {
        LanguageSelect(settingsViewModel)
    }
    if (themeBottomSheet) {
        ThemeSelect(settingsViewModel)
    }
}


@Composable
fun SettingItem(
    group: SettingGroup
) {
    MyCard (
        shape = RectangleShape
    ) {
        Column (
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text(
                text = group.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )

            Column {
                group.settingItems.forEach {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { it.onClick() },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,

                    ) {
                        Row (
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.description,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = it.description,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        IconButton(
                            onClick = it.onClick
                        ) {
                            Icon(
                                imageVector = CssGgIcons.ChevronRight,
                                contentDescription = stringResource(Res.string.forward),
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelect(
    settingsViewModel: SettingsViewModel
) {
    val languages = listOf(Language.English, Language.Spanish)
    val languageIso by settingsViewModel.languageSelect.collectAsState()
    val localization = koinInject<Localization>()

    LaunchedEffect(languageIso) {
        localization.applyLanguage(languageIso)
    }

    val selectedLanguage by remember(languageIso) {
        derivedStateOf {
            Language.entries.first { it.iso == languageIso }
        }
    }

    ModalBottomSheet(
        onDismissRequest = { settingsViewModel.setLanguageBottomSheet(false) }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.select_language),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
            }

            items(languages) { language ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp)
                        .clickable { settingsViewModel.setLanguage(language.iso) },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = language.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    RadioButton(
                        selected = selectedLanguage == language,
                        onClick = { settingsViewModel.setLanguage(language.iso) }
                    )
                }
                HorizontalDivider()
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelect(
    settingsViewModel: SettingsViewModel
) {
    val theme by settingsViewModel.themeSelect.collectAsState()
    val themes = listOf(Theme.SystemDefault, Theme.Light, Theme.Dark)

    ModalBottomSheet(
        onDismissRequest = { settingsViewModel.setThemeBottomSheet(false) }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.select_theme),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
            }

            items(themes) { appTheme ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp)
                        .clickable { settingsViewModel.setTheme(appTheme) },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text =  appTheme.getThemeName(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    RadioButton(
                        selected = appTheme == theme,
                        onClick = { settingsViewModel.setTheme(appTheme) }
                    )
                }
                HorizontalDivider()
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}