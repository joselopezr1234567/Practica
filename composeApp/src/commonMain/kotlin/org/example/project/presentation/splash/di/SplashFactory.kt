package org.example.project.presentation.splash.di

import org.example.project.data.datasource.FakeAppStatusRemoteDataSource
import org.example.project.data.repository.AppStatusRepositoryImpl
import org.example.project.domain.model.SplashStatus
import org.example.project.domain.usecase.CheckSplashStatusUseCase
import org.example.project.presentation.splash.SplashViewModel

/**
 * Proveedor simple para crear el ViewModel sin un contenedor de DI.
 * Cambia el [scenario] para probar distintos flujos.
 */
object SplashFactory {
    fun create(scenario: SplashStatus = SplashStatus.Authenticated): SplashViewModel {
        val remote = FakeAppStatusRemoteDataSource(scenario)
        val repo = AppStatusRepositoryImpl(remote)
        val useCase = CheckSplashStatusUseCase(repo)
        return SplashViewModel(useCase)
    }
}