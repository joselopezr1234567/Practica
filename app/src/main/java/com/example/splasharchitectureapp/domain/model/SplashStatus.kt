package com.example.splasharchitectureapp.domain.model

// domain/model/SplashStatus.kt
sealed class SplashStatus {
    object Authenticated : SplashStatus()
    object Unauthenticated : SplashStatus()
    object Maintenance : SplashStatus()
    object NoConnection : SplashStatus()
    object ForceUpdate : SplashStatus()
    object OptionalUpdate : SplashStatus()
    object UnsafeDevice : SplashStatus()
}
