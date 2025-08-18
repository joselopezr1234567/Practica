package org.example.project.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import org.example.project.presentation.login.LoginRoot
import org.example.project.presentation.nav.AppScreen
import org.example.project.presentation.splash.SplashEffect
import org.example.project.presentation.splash.SplashRoot

@Composable
fun AppRoot() {
    var screen by remember { mutableStateOf<AppScreen>(AppScreen.Splash) }

    MaterialTheme {
        when (screen) {
            AppScreen.Splash -> SplashRoot(
                onEffect = { eff ->
                    when (eff) {
                        SplashEffect.GoToLogin        -> screen = AppScreen.Login
                        SplashEffect.GoToHome         -> screen = AppScreen.Home
                        SplashEffect.GoToForceUpdate  -> { /* TODO */ }
                        SplashEffect.GoToOptionalUpdate -> { /* TODO */ }
                        SplashEffect.ShowMaintenanceScreen -> { /* TODO */ }
                        SplashEffect.ShowRetryOption  -> { /* puedes mostrar un diálogo */ }
                        SplashEffect.ShowRootedDeviceWarning -> { /* TODO */ }
                    }
                }
            )
            AppScreen.Login -> LoginRoot()
            AppScreen.Home  -> Text("Home (placeholder)") // luego reemplazas por tu Home
        }
    }
}
