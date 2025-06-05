package com.luisdev.marknotes.features.login


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.luisdev.marknotes.core.ui.components.LoginButton
import com.luisdev.marknotes.core.ui.components.MyDashboardBack
import com.luisdev.marknotes.core.ui.components.MyFilledTonalButton
import com.luisdev.marknotes.core.ui.components.MyOutlinedTextField
import com.luisdev.marknotes.domain.model.LoginOption
import com.luisdev.marknotes.domain.model.LoginProvider
import compose.icons.CssGgIcons
import compose.icons.SimpleIcons
import compose.icons.TablerIcons
import compose.icons.simpleicons.Apple
import compose.icons.simpleicons.Facebook
import compose.icons.simpleicons.Github
import io.github.aakira.napier.Napier
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Apple
import io.github.jan.supabase.auth.providers.Facebook
import io.github.jan.supabase.auth.providers.Github
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.createSupabaseClient
import kotlinx.coroutines.launch
import marknotes.composeapp.generated.resources.Res
import marknotes.composeapp.generated.resources.no_account_qs
import marknotes.composeapp.generated.resources.or
import marknotes.composeapp.generated.resources.sign_in
import marknotes.composeapp.generated.resources.sign_in_with
import marknotes.composeapp.generated.resources.sign_up
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navHostController: NavHostController
) {
    MyDashboardBack(
        title = stringResource(Res.string.sign_in),
        onBackClick = { navHostController.popBackStack() },
        content = {
            Screen(
                loginViewModel
            )
        }
    )
}

@Composable
fun Screen(
    loginViewModel: LoginViewModel
) {
    val options = listOf(
        LoginOption("Apple", LoginProvider.IDToken(Apple), SimpleIcons.Apple, null, Color.Black, Color.White, Color(0xFFCDC6B4)),
        LoginOption("Google", LoginProvider.IDToken(Google), null, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Google_%22G%22_logo.svg/768px-Google_%22G%22_logo.svg.png", Color.White, Color.Black, Color(0xFF4B4739)),
        LoginOption("Facebook", LoginProvider.IDToken(Facebook) , SimpleIcons.Facebook, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/2023_Facebook_icon.svg/2048px-2023_Facebook_icon.svg.png", Color(0xFF1877F2), Color.White, Color(0xFFCDC6B4)),
        LoginOption("GitHub", LoginProvider.OAuth(Github), SimpleIcons.Github, null,  Color(0xFF3C3C3C), Color.White, Color(0xFFCDC6B4))
    )
    val isLoading by loginViewModel.isLoading.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.2f)),
                contentAlignment = Alignment.TopCenter
            ) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            item {
                MyOutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = "Email",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Spacer(Modifier.height(12.dp))
                MyOutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = "Password",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Spacer(Modifier.height(12.dp))
                MyFilledTonalButton(
                    text = stringResource(Res.string.sign_in),
                    enabled = true,
                    modifier = Modifier.fillMaxWidth(),
                    onClickAction = {}
                )
            }

            item {
                Spacer(Modifier.height(12.dp))
                Row (
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(Res.string.no_account_qs),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
//                    Spacer(Modifier.width(4.dp))
                    TextButton(
                        modifier = Modifier.padding(0.dp),
                        onClick = {}
                    ) {
                        Text(
                            text = stringResource(Res.string.sign_up),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }

            item {
                Spacer(Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(Res.string.or),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f))
                }
                Spacer(Modifier.height(32.dp))
            }

            items(options) { option ->
                LoginButton(
                    text = "${stringResource(Res.string.sign_in_with)} ${option.name}",
                    onClickAction = {
                        loginViewModel.signInWithOAuth(option.provider)
                    },
                    buttonColor = option.colorContainer,
                    textColor = option.colorText,
                    icon = option.icon,
                    urlImage = option.urlImage,
                    borderColor = option.borderColor
                )
                Spacer(Modifier.height(12.dp))
            }

        }
    }
}
