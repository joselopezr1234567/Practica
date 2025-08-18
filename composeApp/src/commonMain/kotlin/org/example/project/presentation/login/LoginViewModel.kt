package org.example.project.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.example.project.domain.usecase.LoginUseCase

class LoginViewModel(
    private val login: LoginUseCase
) : ViewModel() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _state = MutableStateFlow<LoginState>(LoginState.Editing())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _effects = Channel<LoginEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    fun onEmailChange(value: String) {
        val s = _state.value
        if (s is LoginState.Editing) _state.value = s.copy(email = value)
    }

    fun onPasswordChange(value: String) {
        val s = _state.value
        if (s is LoginState.Editing) _state.value = s.copy(password = value)
    }

    fun onTogglePasswordVisibility() {
        val s = _state.value
        if (s is LoginState.Editing) _state.value = s.copy(isPasswordVisible = !s.isPasswordVisible)
    }

    fun onSubmit() {
        val s = _state.value
        if (s !is LoginState.Editing) return

        scope.launch {
            _state.value = LoginState.Loading
            runCatching { login(s.email, s.password) }
                .onSuccess { result ->
                    when (result) {
                        is AuthResult.Success -> {
                            _state.value = LoginState.Success(result.user)
                            _effects.send(LoginEffect.GoToHome)
                        }
                        is AuthResult.Failure -> {
                            when (result.error) {
                                AuthError.InvalidCredentials ->
                                    _effects.send(LoginEffect.ShowInvalidCredentials)
                                AuthError.NetworkFailure, AuthError.ServerError ->
                                    _effects.send(LoginEffect.ShowRetryOption)
                                AuthError.LockedOut ->
                                    _effects.send(LoginEffect.ShowLockedOut)
                                is AuthError.Unknown -> { /* puede loguearse */ }
                            }
                            _state.value = LoginState.Error(message = result.error.toMessage())
                        }
                    }
                }
                .onFailure { e ->
                    _state.value = LoginState.Error(e.message ?: "Unknown error")
                    _effects.trySend(LoginEffect.ShowRetryOption)
                }
        }
    }

    fun onRetry() {
        // Vuelve al estado de edición para permitir nuevo intento
        _state.value = LoginState.Editing()
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}

private fun AuthError.toMessage(): String = when (this) {
    AuthError.InvalidCredentials -> "Credenciales inválidas"
    AuthError.NetworkFailure     -> "Sin conexión"
    AuthError.ServerError        -> "Error del servidor"
    AuthError.LockedOut          -> "Cuenta bloqueada"
    is AuthError.Unknown         -> cause.message ?: "Error desconocido"
}
