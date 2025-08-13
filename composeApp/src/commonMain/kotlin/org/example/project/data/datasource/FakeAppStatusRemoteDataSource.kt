package org.example.project.data.datasource

import kotlinx.coroutines.delay
import org.example.project.domain.model.SplashStatus

/**
 * Simulador de backend. Cambia el parámetro [scenario] para probar flujos.
 */
class FakeAppStatusRemoteDataSource(
    private val scenario: SplashStatus = SplashStatus.Unauthenticated,
    private val networkDelayMs: Long = 300
) {
    suspend fun fetchAppStatus(): SplashStatus {
        delay(networkDelayMs)
        return scenario
    }
}