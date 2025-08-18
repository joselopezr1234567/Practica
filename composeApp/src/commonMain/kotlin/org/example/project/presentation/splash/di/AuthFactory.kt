package org.example.project.presentation.di

import org.example.project.data.datasource.FakeAuthRemoteDataSource
import org.example.project.data.repository.AuthRepositoryImpl
import org.example.project.domain.repository.AuthRepository
import org.example.project.domain.usecase.LoginUseCase
import org.example.project.presentation.login.LoginViewModel

/**
 * DI simple para Login (sin botones). Ubicado en presentation/di para
 * seguir el mismo patrón que SplashFactory.
 */
object AuthFactory {
    private val remote = FakeAuthRemoteDataSource()
    private val repo: AuthRepository = AuthRepositoryImpl(remote)
    private val useCase = LoginUseCase(repo)

    fun createViewModel(): LoginViewModel = LoginViewModel(useCase)
}
