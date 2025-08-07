package com.example.splasharchitectureapp.domain.usecase

import com.example.splasharchitectureapp.domain.model.SplashStatus
import com.example.splasharchitectureapp.domain.repository.AppStatusRepository

// domain/usecase/CheckSplashStatusUseCase.kt
class CheckSplashStatusUseCase(
    private val repository: AppStatusRepository
) {
    suspend operator fun invoke(): SplashStatus {
        return repository.getSplashStatus()
    }
}
