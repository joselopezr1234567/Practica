package com.example.splasharchitectureapp.domain.repository

import com.example.splasharchitectureapp.domain.model.SplashStatus

// domain/repository/AppStatusRepository.kt
interface AppStatusRepository {
    suspend fun getSplashStatus(): SplashStatus
}
