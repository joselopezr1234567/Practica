package org.example.project.data.datasource

import kotlinx.coroutines.delay
import org.example.project.domain.model.SplashStatus

/**
 * Simulador de backend con selección dinámica de estado.
 * Llama a [select] desde tus botones para cambiar el estado que devolverá [fetchAppStatus].
 */
class FakeAppStatusRemoteDataSource(
    scenario: SplashStatus = SplashStatus.Authenticated,
    private val networkDelayMs: Long = 25
) {
    private var currentScenario: SplashStatus = scenario

    /** Cambia el estado simulado que devolverá el backend. */
    fun select(status: SplashStatus) { currentScenario = status }

    // Helpers opcionales para usar directamente desde los botones
    fun selectAuthenticated()   = select(SplashStatus.Authenticated)
    fun selectUnauthenticated() = select(SplashStatus.Unauthenticated)
    fun selectForceUpdate()     = select(SplashStatus.ForceUpdate)
    fun selectOptionalUpdate()  = select(SplashStatus.OptionalUpdate)
    fun selectMaintenance()     = select(SplashStatus.Maintenance)
    fun selectNoConnection()    = select(SplashStatus.NoConnection)
    fun selectServerError()     = select(SplashStatus.ServerError)
    fun selectUnsafeDevice()    = select(SplashStatus.UnsafeDevice)

    suspend fun fetchAppStatus(): SplashStatus {
        delay(networkDelayMs)
        return currentScenario
    }
}