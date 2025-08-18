package org.example.project.domain.repository

import org.example.project.domain.model.Email
import org.example.project.domain.model.Password
import org.example.project.presentation.login.AuthResult

interface AuthRepository {
    suspend fun login(email: Email, password: Password): AuthResult
}
