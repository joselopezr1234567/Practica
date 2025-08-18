package org.example.project.presentation.login

import org.example.project.domain.model.User

sealed interface AuthResult {
    data class Success(val user: User) : AuthResult
    data class Failure(val error: AuthError) : AuthResult
}

sealed interface AuthError {
    data object InvalidCredentials : AuthError
    data object NetworkFailure : AuthError
    data object ServerError : AuthError
    data object LockedOut : AuthError
    data class Unknown(val cause: Throwable) : AuthError
}
