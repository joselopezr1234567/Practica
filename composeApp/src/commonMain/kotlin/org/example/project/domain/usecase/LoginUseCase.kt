package org.example.project.domain.usecase

import org.example.project.domain.model.Email
import org.example.project.domain.model.Password
import org.example.project.domain.repository.AuthRepository
import org.example.project.presentation.login.AuthError
import org.example.project.presentation.login.AuthResult

/**
 * Valida campos mínimos y delega en el repositorio.
 */
class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(usernameOrEmail: String, rawPassword: String): AuthResult {
        if (usernameOrEmail.isBlank() || rawPassword.isBlank()) {
            return AuthResult.Failure(AuthError.InvalidCredentials)
        }
        // Valida formato de email con VO
        val email = Email(usernameOrEmail)
        val password = Password(rawPassword)
        return repository.login(email, password)
    }
}
