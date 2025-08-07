// presentation/splash/SplashScreen.kt
package com.example.splasharchitectureapp.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SplashScreen(viewModel: SplashViewModel) {
    val state = viewModel.state
    val effect = viewModel.effect

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is SplashState.Loading -> {
                CircularProgressIndicator()
            }

            is SplashState.Success -> {
                when (effect) {
                    is SplashEffect.GoToHome -> Text("Navegar a Home")
                    is SplashEffect.GoToLogin -> Text("Navegar a Login")
                    is SplashEffect.GoToForceUpdate -> Text("Actualizar obligatorio")
                    is SplashEffect.GoToOptionalUpdate -> Text("Actualizar opcional")
                    is SplashEffect.ShowMaintenanceScreen -> Text("En mantenimiento")
                    is SplashEffect.ShowRetryOption -> Text("Sin conexión. Reintentar")
                    is SplashEffect.ShowRootedDeviceWarning -> Text("Dispositivo no seguro")
                    null -> Text("Listo")
                }
            }

            is SplashState.Error -> {
                Text("Error al cargar. Intenta nuevamente.")
            }
        }
    }
}
