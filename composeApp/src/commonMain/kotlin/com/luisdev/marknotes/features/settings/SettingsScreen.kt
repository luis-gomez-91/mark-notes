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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.luisdev.marknotes.core.navigation.AppVersion
import com.luisdev.marknotes.core.navigation.Login
import com.luisdev.marknotes.core.navigation.PrivacyPolicy
import com.luisdev.marknotes.core.navigation.SuscriptionOptions
import com.luisdev.marknotes.core.navigation.TermsCondition
import com.luisdev.marknotes.core.ui.components.LoginButton
import com.luisdev.marknotes.core.ui.components.MyCard
import com.luisdev.marknotes.core.ui.components.MyDashboardBack
import com.luisdev.marknotes.core.ui.components.MyFilledTonalButton
import com.luisdev.marknotes.core.ui.components.MyHorizontalDivider
import com.luisdev.marknotes.core.utils.Language
import com.luisdev.marknotes.core.utils.Localization
import com.luisdev.marknotes.core.utils.Theme
import com.luisdev.marknotes.core.utils.getThemeName
import com.luisdev.marknotes.domain.model.LoginOption
import com.luisdev.marknotes.domain.model.LoginProvider
import com.luisdev.marknotes.domain.model.SettingGroup
import com.luisdev.marknotes.domain.model.SettingItem
import com.luisdev.marknotes.features.login.LoginViewModel
import compose.icons.CssGgIcons
import compose.icons.SimpleIcons
import compose.icons.cssggicons.ChevronRight
import compose.icons.cssggicons.Crown
import compose.icons.cssggicons.EditContrast
import compose.icons.cssggicons.FileDocument
import compose.icons.cssggicons.GlobeAlt
import compose.icons.cssggicons.Info
import compose.icons.cssggicons.Lock
import compose.icons.cssggicons.LogIn
import compose.icons.cssggicons.LogOut
import compose.icons.cssggicons.User
import compose.icons.simpleicons.Apple
import compose.icons.simpleicons.Facebook
import compose.icons.simpleicons.Github
import compose.icons.simpleicons.Google
import io.github.aakira.napier.Napier
import io.github.jan.supabase.auth.providers.Apple
import io.github.jan.supabase.auth.providers.Facebook
import io.github.jan.supabase.auth.providers.Github
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.user.UserSession
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.jsonPrimitive
import marknotes.composeapp.generated.resources.Res
import marknotes.composeapp.generated.resources.account
import marknotes.composeapp.generated.resources.app
import marknotes.composeapp.generated.resources.app_version
import marknotes.composeapp.generated.resources.authenticated_with
import marknotes.composeapp.generated.resources.forward
import marknotes.composeapp.generated.resources.language
import marknotes.composeapp.generated.resources.last_sign_in
import marknotes.composeapp.generated.resources.member_since
import marknotes.composeapp.generated.resources.not_active
import marknotes.composeapp.generated.resources.preferences
import marknotes.composeapp.generated.resources.privacy_policy
import marknotes.composeapp.generated.resources.select_language
import marknotes.composeapp.generated.resources.select_theme
import marknotes.composeapp.generated.resources.settings
import marknotes.composeapp.generated.resources.sign_in
import marknotes.composeapp.generated.resources.sign_in_with
import marknotes.composeapp.generated.resources.sign_out
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
    loginViewModel: LoginViewModel
) {
    MyDashboardBack(
        title = stringResource(Res.string.settings),
        onBackClick = { navHostController.popBackStack() },
        content = {
            Screen(
                navHostController, settingsViewModel, loginViewModel
            )
        }
    )
}

@Composable
fun Screen(
    navHostController: NavHostController,
    settingsViewModel: SettingsViewModel,
    loginViewModel: LoginViewModel
) {
    val languageBottomSheet by settingsViewModel.languageBottomSheet.collectAsState(false)
    val themeBottomSheet by settingsViewModel.themeBottomSheet.collectAsState(false)
    val accountBottomSheet by settingsViewModel.accountBottomSheet.collectAsState(false)
    val session by loginViewModel.session.collectAsState(null)

    val preferences = SettingGroup(
        title = stringResource(Res.string.preferences),
        settingItems = listOf(
            SettingItem(
                title = stringResource(Res.string.theme),
                icon = CssGgIcons.EditContrast,
                onClick = { settingsViewModel.setThemeBottomSheet(true) }
            ),
            SettingItem(
                title = stringResource(Res.string.language),
                icon = CssGgIcons.GlobeAlt,
                onClick = { settingsViewModel.setLanguageBottomSheet(true) }
            )
        )
    )

    val app = SettingGroup(
        title = stringResource(Res.string.app),
        settingItems = listOf(
            SettingItem(
                title = stringResource(Res.string.terms_conditions),
                icon = CssGgIcons.FileDocument,
                onClick = { navHostController.navigate(TermsCondition) }
            ),
            SettingItem(
                title = stringResource(Res.string.privacy_policy),
                icon = CssGgIcons.Lock,
                onClick = { navHostController.navigate(PrivacyPolicy) }
            ),
            SettingItem(
                title = stringResource(Res.string.app_version),
                icon = CssGgIcons.Info,
                onClick = { navHostController.navigate(AppVersion) }
            )
        )
    )

    val account = SettingGroup(
        title = stringResource(Res.string.account),
        settingItems = listOf(
        when (session) {
            null -> {
                SettingItem(
                    title = stringResource(Res.string.sign_in),
                    icon = CssGgIcons.LogIn,
                    onClick = {
                        navHostController.navigate(Login)
//                        settingsViewModel.setLoginOpcionsBottomSheet(true)
                    }
                )
            }
            else -> {
                SettingItem(
                    title = stringResource(Res.string.account),
                    description = session?.user?.email,
                    icon = CssGgIcons.User,
                    onClick = { settingsViewModel.setAccountBottomSheet(true) }
                )
            }
        }
        )
    )

    val suscription = SettingGroup(
        title = stringResource(Res.string.subscription),
        settingItems = listOf(
            SettingItem(
                title = stringResource(Res.string.subscribe_now),
                description = "${stringResource(Res.string.status)}: ${stringResource(Res.string.not_active)}",
                icon = CssGgIcons.Crown,
                onClick = { navHostController.navigate(SuscriptionOptions) }
            ),
        )
    )

    val settingGroups = listOf(account, suscription, preferences, app)

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
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

    if (accountBottomSheet) {
        session?.let { AccountOptions(settingsViewModel, loginViewModel, it) }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountOptions(
    settingsViewModel: SettingsViewModel,
    loginViewModel: LoginViewModel,
    session: UserSession
) {
    val user = session.user
    val email = user?.email ?: "Correo no disponible"
    val userId = user?.id ?: "ID desconocido"
    val authProvider = user?.appMetadata?.get("provider")?.jsonPrimitive?.content?: ""
    val fullName = (user?.userMetadata?.get("full_name")?.jsonPrimitive?.content)
        ?: (user?.email?.split("@")?.firstOrNull() ?: "Usuario")

    val createdAt = session.user?.createdAt?.let { createdAtString ->
        val instant = Instant.parse(createdAtString.toString()) // Parsea el String ISO8601
        val localDate = instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
        localDate.toString()  // Convierte LocalDate a "YYYY-MM-DD"
    } ?: "Fecha no disponible"

    val lastSignInAt = user?.lastSignInAt?.let { lastSignInIso ->
        try {
            val instant = Instant.parse(lastSignInIso.toString())
            val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
            "${localDateTime.date} ${localDateTime.hour.toString().padStart(2, '0')}:${localDateTime.minute.toString().padStart(2, '0')}"
        } catch (e: Exception) {
            "Último inicio de sesión no disponible"
        }
    } ?: "Último inicio de sesión no disponible"

    val avatarUrl = (session.user?.userMetadata)
        ?.get("avatar_url")
        ?.jsonPrimitive
        ?.content

    ModalBottomSheet(
        onDismissRequest = { settingsViewModel.setAccountBottomSheet(false) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            // Use Arrangement.spacedBy for consistent spacing if preferred
            verticalArrangement = Arrangement.spacedBy(8.dp) // Default spacing
        ) {
            // Avatar
            AsyncImage(
                model = avatarUrl,
                contentDescription = "User Avatar", // Provide a meaningful description
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                // Add error/placeholder if image fails to load
                // placeholder = painterResource(id = R.drawable.ic_default_avatar),
                // error = painterResource(id = R.drawable.ic_error_avatar)
            )

            Spacer(modifier = Modifier.height(8.dp)) // More space after avatar

            // Name
            Text(
                text = fullName,
                style = MaterialTheme.typography.headlineSmall, // Larger for name
                color = MaterialTheme.colorScheme.onSurface
            )

            // Email
            Text(
                text = email,
                style = MaterialTheme.typography.bodyLarge, // Slightly larger than bodyMedium
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // ID del usuario (maybe useful for support, less prominent)
            Text(
                text = "ID: $userId",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )

            // Authentication Provider
            Text(
                text = "${stringResource(Res.string.authenticated_with)} ${authProvider.capitalize()}", // Capitalize for display
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )

            // Fecha de creación
            Text(
                text = "${stringResource(Res.string.member_since)} $createdAt",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )

            // Last Sign-in Date
            Text(
                text = "${stringResource(Res.string.last_sign_in)}: $lastSignInAt",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.height(8.dp))

            MyFilledTonalButton(
                onClickAction = {
                    loginViewModel.signOut()
                    settingsViewModel.setAccountBottomSheet(false)
                },
                text = stringResource(Res.string.sign_out),
                buttonColor = MaterialTheme.colorScheme.errorContainer,
                textColor = MaterialTheme.colorScheme.error,
                icon = CssGgIcons.LogOut
            )
        }
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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { it.onClick?.let { it1 -> it1() } }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = it.description,
                            tint = MaterialTheme.colorScheme.inverseSurface,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(Modifier.width(8.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = it.title,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.inverseSurface
                            )
                            it.description?.let { desc ->
                                Text(
                                    text = desc,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            }
                        }

                        Icon(
                            imageVector = CssGgIcons.ChevronRight,
                            contentDescription = stringResource(Res.string.forward),
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(20.dp)
                        )
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
                MyHorizontalDivider()
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
                MyHorizontalDivider()
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
                MyHorizontalDivider()
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
                MyHorizontalDivider()
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}