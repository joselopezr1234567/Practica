// presentation/splash/SplashViewModel.kt
package com.example.splasharchitectureapp.presentation.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splasharchitectureapp.domain.usecase.CheckSplashStatusUseCase
import com.example.splasharchitectureapp.domain.model.SplashStatus
import kotlinx.coroutines.launch

class SplashViewModel(
    private val checkSplashStatusUseCase: CheckSplashStatusUseCase
) : ViewModel() {

    // Estado de la UI (loading, success, error)
    var state by mutableStateOf<SplashState>(SplashState.Loading)
        private set

    // Efecto de UI (navegar, mostrar alerta, etc.)
    var effect: SplashEffect? by mutableStateOf(null)
        private set

    // Al crear el ViewModel, inicia el chequeo de estado
    init {
        checkAppStatus()
    }

    // Ejecuta el UseCase y actualiza state y effect
    private fun checkAppStatus() {
        viewModelScope.launch {
            try {
                val status = checkSplashStatusUseCase()
                state = SplashState.Success
                effect = when (status) {
                    is SplashStatus.Authenticated -> SplashEffect.GoToHome
                    is SplashStatus.Unauthenticated -> SplashEffect.GoToLogin
                    is SplashStatus.Maintenance -> SplashEffect.ShowMaintenanceScreen
                    is SplashStatus.NoConnection -> SplashEffect.ShowRetryOption
                    is SplashStatus.ForceUpdate -> SplashEffect.GoToForceUpdate
                    is SplashStatus.OptionalUpdate -> SplashEffect.GoToOptionalUpdate
                    is SplashStatus.UnsafeDevice -> SplashEffect.ShowRootedDeviceWarning
                }
            } catch (e: Exception) {
                state = SplashState.Error
                effect = SplashEffect.ShowRetryOption
            }
        }
    }
}
