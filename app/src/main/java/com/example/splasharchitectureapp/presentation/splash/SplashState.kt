package com.example.splasharchitectureapp.presentation.splash

// presentation/splash/SplashState.kt
sealed class SplashState {
    object Loading : SplashState()
    object Success : SplashState()
    object Error : SplashState()
}
