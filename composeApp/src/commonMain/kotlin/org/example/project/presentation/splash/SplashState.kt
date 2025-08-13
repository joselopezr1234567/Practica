package org.example.project.presentation.splash

import org.example.project.domain.model.SplashStatus

sealed interface SplashState {
    data object Idle : SplashState
    data object Loading : SplashState
    data class Success(val status: SplashStatus) : SplashState
    data class Error(val status: SplashStatus, val message: String? = null) : SplashState
}