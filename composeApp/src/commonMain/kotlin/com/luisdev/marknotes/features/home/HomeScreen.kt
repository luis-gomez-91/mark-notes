package com.luisdev.marknotes.features.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.luisdev.marknotes.core.ui.components.MyDashBoardScreen
import dev.icerock.moko.resources.compose.stringResource
import com.luisdev.marknotes.MR

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    MyDashBoardScreen(
        title = "Hola mundo",
        navController = navController,
        content = {
            Screen()
        }
    )

}

@Composable
fun Screen(

) {
    Text("HOLA MUNDO")
    Text(text = stringResource(MR.strings.titulo_bienvenida))

}