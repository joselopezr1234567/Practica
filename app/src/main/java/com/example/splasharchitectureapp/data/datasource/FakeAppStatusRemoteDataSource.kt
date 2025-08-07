package com.example.splasharchitectureapp.data.datasource

import com.example.splasharchitectureapp.domain.model.SplashStatus
// data/datasource/FakeAppStatusRemoteDataSource.kt
class FakeAppStatusRemoteDataSource {
    suspend fun fetchAppStatus(): SplashStatus {
        // Aquí puedes simular distintos estados para probar
        return SplashStatus.Unauthenticated
    }
}
