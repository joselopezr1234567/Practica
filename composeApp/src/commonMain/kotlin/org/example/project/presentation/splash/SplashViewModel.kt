package org.example.project.presentation.splash

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
import org.example.project.domain.model.SplashStatus
import org.example.project.domain.usecase.CheckSplashStatusUseCase

/**
 * ViewModel multiplataforma (usa androidx.lifecycle MPP de JetBrains).
 * Expone un StateFlow de estado y un Flow de efectos de navegación.
 */
class SplashViewModel(
    private val checkSplashStatus: CheckSplashStatusUseCase
) : ViewModel() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _state = MutableStateFlow<SplashState>(SplashState.Idle)
    val state: StateFlow<SplashState> = _state.asStateFlow()

    private val _effects = Channel<SplashEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    fun checkStatus() {
        scope.launch {
            _state.value = SplashState.Loading
            runCatching { checkSplashStatus() }
                .onSuccess { status ->
                    when (status) {
                        SplashStatus.Authenticated -> {
                            _state.value = SplashState.Success(status)
                            _effects.send(SplashEffect.GoToHome)
                        }
                        SplashStatus.Unauthenticated -> {
                            _state.value = SplashState.Success(status)
                            _effects.send(SplashEffect.GoToLogin)
                        }
                        SplashStatus.ForceUpdate -> {
                            _state.value = SplashState.Success(status)
                            _effects.send(SplashEffect.GoToForceUpdate)
                        }
                        SplashStatus.OptionalUpdate -> {
                            _state.value = SplashState.Success(status)
                            _effects.send(SplashEffect.GoToOptionalUpdate)
                        }
                        SplashStatus.Maintenance -> {
                            _state.value = SplashState.Success(status)
                            _effects.send(SplashEffect.ShowMaintenanceScreen)
                        }
                        SplashStatus.NoConnection -> {
                            _state.value = SplashState.Error(status, "Sin conexión a internet")
                            _effects.send(SplashEffect.ShowRetryOption)
                        }
                        SplashStatus.ServerError -> {
                            _state.value = SplashState.Error(status, "Error del servidor")
                            _effects.send(SplashEffect.ShowRetryOption)
                        }
                        SplashStatus.UnsafeDevice -> {
                            _state.value = SplashState.Success(status)
                            _effects.send(SplashEffect.ShowRootedDeviceWarning)
                        }
                    }
                }
                .onFailure { e ->
                    _state.value = SplashState.Error(SplashStatus.ServerError, e.message)
                    _effects.trySend(SplashEffect.ShowRetryOption)
                }
        }
    }

    fun onRetry() = checkStatus()

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}