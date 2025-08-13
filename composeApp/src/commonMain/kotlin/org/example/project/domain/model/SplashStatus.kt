package org.example.project.domain.model

sealed interface SplashStatus {
    data object Authenticated : SplashStatus
    data object Unauthenticated : SplashStatus
    data object ForceUpdate : SplashStatus
    data object OptionalUpdate : SplashStatus
    data object Maintenance : SplashStatus
    data object NoConnection : SplashStatus
    data object ServerError : SplashStatus
    data object UnsafeDevice : SplashStatus

}