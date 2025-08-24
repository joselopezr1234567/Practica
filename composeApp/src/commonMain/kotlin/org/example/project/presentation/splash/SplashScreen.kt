package org.example.project.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.project.domain.model.SplashStatus
import org.example.project.presentation.splash.di.SplashFactory
import org.example.project.presentation.splash.SplashState
import org.example.project.presentation.splash.SplashViewModel
import org.example.project.presentation.splash.SplashEffect

@Composable
fun SplashScreen(scenario: SplashStatus = SplashStatus.Unauthenticated) {
    // Crea el VM SOLO cuando cambie 'scenario'
    val vm = remember(scenario) { SplashFactory.create(scenario) }
    val state by vm.state.collectAsState()

    // Corre una vez por instancia de VM (o cuando cambie el 'scenario')
    LaunchedEffect(vm) { 
        vm.checkStatus()
        // Observa efectos y navega según corresponda
        vm.effects.collect { effect ->
            when (effect) {
                SplashEffect.GoToLogin -> {
                    // TODO: Implementar navegación al login
                    println("Navegando al login...")
                }
                SplashEffect.GoToHome -> {
                    // TODO: Implementar navegación al home
                    println("Navegando al home...")
                }
                else -> {
                    // Otros efectos
                    println("Efecto recibido: $effect")
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val current = state) {
            SplashState.Idle, SplashState.Loading -> CircularProgressIndicator()
            is SplashState.Success -> Text("OK: ${current.status}")
            is SplashState.Error -> Text("Error: ${current.message ?: ""}")
        }
    }
}

