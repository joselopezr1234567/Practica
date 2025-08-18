package org.example.project.presentation.login

import org.example.project.domain.model.User

sealed interface LoginState {
    data class Editing(
        val email: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false
    ) : LoginState

    data object Loading : LoginState
    data class Success(val user: User) : LoginState
    data class Error(val message: String) : LoginState
}
