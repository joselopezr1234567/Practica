package org.example.project.presentation.splash

sealed interface SplashEffect {
    data object GoToHome : SplashEffect
    data object GoToLogin : SplashEffect
    data object GoToForceUpdate : SplashEffect
    data object GoToOptionalUpdate : SplashEffect
    data object ShowMaintenanceScreen : SplashEffect
    data object ShowRetryOption : SplashEffect
    data object ShowRootedDeviceWarning : SplashEffect
}