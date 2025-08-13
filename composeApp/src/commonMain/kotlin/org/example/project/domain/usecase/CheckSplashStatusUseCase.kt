package org.example.project.domain.usecase

import org.example.project.domain.model.SplashStatus
import org.example.project.domain.repository.AppStatusRepository

/**
 * Contiene la lógica de negocio para decidir el estado del Splash.
 * Por ahora delega en el repositorio, pero aquí puedes agregar reglas extra
 * (por ejemplo, validación de versiones, flags remotos, etc.).
 */
class CheckSplashStatusUseCase(
    private val repository: AppStatusRepository
) {
    suspend operator fun invoke(): SplashStatus {
        return repository.getSplashStatus()
    }
}