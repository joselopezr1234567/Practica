package com.example.splasharchitectureapp.presentation.splash

// presentation/splash/SplashEffect.kt
sealed class SplashEffect {
    object GoToHome : SplashEffect()
    object GoToLogin : SplashEffect()
    object GoToForceUpdate : SplashEffect()
    object GoToOptionalUpdate : SplashEffect()
    object ShowMaintenanceScreen : SplashEffect()
    object ShowRetryOption : SplashEffect()
    object ShowRootedDeviceWarning : SplashEffect()
}
