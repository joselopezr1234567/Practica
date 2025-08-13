package org.example.project.domain.repository

import org.example.project.domain.model.SplashStatus

interface AppStatusRepository {
    suspend fun getSplashStatus(): SplashStatus
}