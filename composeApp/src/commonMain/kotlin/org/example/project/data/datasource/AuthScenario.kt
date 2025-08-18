package org.example.project.data.datasource

/** Escenarios de respuesta simulada del backend de autenticación. */
sealed interface AuthScenario {
    data object Success : AuthScenario
    data object InvalidCredentials : AuthScenario
    data object NetworkFailure : AuthScenario
    data object ServerError : AuthScenario
    data object LockedOut : AuthScenario
}
