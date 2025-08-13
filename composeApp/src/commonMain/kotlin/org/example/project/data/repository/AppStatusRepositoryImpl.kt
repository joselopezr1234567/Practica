package org.example.project.data.repository

import org.example.project.data.datasource.FakeAppStatusRemoteDataSource
import org.example.project.domain.model.SplashStatus
import org.example.project.domain.repository.AppStatusRepository

class AppStatusRepositoryImpl(
    private val remote: FakeAppStatusRemoteDataSource
) : AppStatusRepository {
    override suspend fun getSplashStatus(): SplashStatus = remote.fetchAppStatus()
}