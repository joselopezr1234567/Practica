package com.example.splasharchitectureapp.data.repository

import com.example.splasharchitectureapp.domain.model.SplashStatus
import com.example.splasharchitectureapp.domain.repository.AppStatusRepository
import com.example.splasharchitectureapp.data.datasource.FakeAppStatusRemoteDataSource
// data/repository/AppStatusRepositoryImpl.kt
class AppStatusRepositoryImpl(
    private val remoteDataSource: FakeAppStatusRemoteDataSource
) : AppStatusRepository {
    override suspend fun getSplashStatus(): SplashStatus {
        return remoteDataSource.fetchAppStatus()
    }
}
