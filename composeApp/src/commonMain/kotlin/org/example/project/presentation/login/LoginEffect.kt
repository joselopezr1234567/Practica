package org.example.project.presentation.login

sealed interface LoginEffect {
    data object GoToHome : LoginEffect
    data object ShowInvalidCredentials : LoginEffect
    data object ShowRetryOption : LoginEffect
    data object ShowLockedOut : LoginEffect
}
