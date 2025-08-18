package org.example.project.data.datasource

import kotlinx.coroutines.delay
import org.example.project.domain.model.Email
import org.example.project.domain.model.Password
import org.example.project.domain.model.User
import org.example.project.domain.model.UserId
import org.example.project.presentation.login.AuthError
import org.example.project.presentation.login.AuthResult

/**
 * Simulador de backend para Login con selección dinámica de escenario.
 * Igual enfoque que FakeAppStatusRemoteDataSource (Splash).
 */
class FakeAuthRemoteDataSource(
    scenario: AuthScenario = AuthScenario.Success,
    private val networkDelayMs: Long = 25
) {
    private var currentScenario: AuthScenario = scenario

    fun select(s: AuthScenario) { currentScenario = s }

    // Helpers para botones:
    fun selectSuccess()            = select(AuthScenario.Success)
    fun selectInvalidCredentials() = select(AuthScenario.InvalidCredentials)
    fun selectNetworkFailure()     = select(AuthScenario.NetworkFailure)
    fun selectServerError()        = select(AuthScenario.ServerError)
    fun selectLockedOut()          = select(AuthScenario.LockedOut)

    suspend fun authenticate(email: Email, password: Password): AuthResult {
        delay(networkDelayMs)
        return when (currentScenario) {
            AuthScenario.Success ->
                AuthResult.Success(User(UserId("1"), name = email.value.substringBefore('@'), email = email))
            AuthScenario.InvalidCredentials -> AuthResult.Failure(AuthError.InvalidCredentials)
            AuthScenario.NetworkFailure     -> AuthResult.Failure(AuthError.NetworkFailure)
            AuthScenario.ServerError        -> AuthResult.Failure(AuthError.ServerError)
            AuthScenario.LockedOut          -> AuthResult.Failure(AuthError.LockedOut)
        }
    }
}
