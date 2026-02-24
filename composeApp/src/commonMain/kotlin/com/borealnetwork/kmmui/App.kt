package com.borealnetwork.kmmui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.borealnetwork.kmmuicore.domain.dialog.DialogParams
import com.borealnetwork.kmmuicore.ui.components.SemiBoldText
import com.borealnetwork.kmmuicore.ui.components.dialog.QuestionAlertDialog
import com.borealnetwork.kmmuicore.utils.toAnnotateString
import io.github.baudelioandalon.kmmuicore.drawable.Res
import io.github.baudelioandalon.kmmuicore.drawable.ic_design_visibility
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

sealed class NavigationScreen(
    val route: String,
    val title: String,
    val icon: DrawableResource = Res.drawable.ic_design_visibility
) {
    data object HomeScreen :
        NavigationScreen("home_screen", "HOME", Res.drawable.ic_design_visibility)

    data object ProfileScreen :
        NavigationScreen("profile_screen", "PROFILE", Res.drawable.ic_design_visibility)

    data object LoginScreen :
        NavigationScreen("login_screen", "LOGIN", Res.drawable.ic_design_visibility)

    data object RecoveryPasswordScreen : NavigationScreen(
        "recovery_password_screen",
        "RECOVERY",
        Res.drawable.ic_design_visibility
    )

    data object ChangePasswordScreen :
        NavigationScreen(
            "change_password_screen",
            "CHANGE PASSWORD",
            Res.drawable.ic_design_visibility
        )

    data object DefaultScreen :
        NavigationScreen("home_screen", "DEFAULT", Res.drawable.ic_design_visibility)

}

@Composable
@Preview
fun App(navController: NavController = rememberNavController()) {
    MaterialTheme {
        var showDialog by remember { mutableStateOf(Pair(false, DialogParams.EMPTY_DIALOG)) }

        LaunchedEffect(Unit){
            showDialog = DialogParams(
                title = "Datos incorrectos",
                confirmText = "Aceptar",
                description = "Revísalos y vuelve a intentarlo. Si el error persiste, contacta a tu asesor.".toAnnotateString(),
                timer = 5
            ).build(
                onConfirm = {
                    showDialog = DialogParams.HIDE_DIALOG
                },
                onDismiss = {
                    showDialog = DialogParams.HIDE_DIALOG
                }
            )
        }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SemiBoldText(
                text = "SemiboldExample"
            )
            Icon(
                painter = painterResource(resource = Res.drawable.ic_design_visibility),
                contentDescription = "Visibility Icon"
            )

            if (showDialog.first) {
                QuestionAlertDialog(
                    params = showDialog.second
                )
            }

        }
    }
}