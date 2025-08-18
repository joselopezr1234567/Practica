package org.example.project.data.repository

import org.example.project.data.datasource.FakeAuthRemoteDataSource
import org.example.project.domain.model.Email
import org.example.project.domain.model.Password
import org.example.project.domain.repository.AuthRepository
import org.example.project.presentation.login.AuthResult

class AuthRepositoryImpl(
    private val remote: FakeAuthRemoteDataSource
) : AuthRepository {

    override suspend fun login(email: Email, password: Password): AuthResult {
        return remote.authenticate(email, password)
    }
}
