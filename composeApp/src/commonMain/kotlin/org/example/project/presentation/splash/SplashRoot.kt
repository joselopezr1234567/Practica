package org.example.project.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.project.presentation.splash.di.SplashFactory

@Composable
fun SplashRoot(
    onEffect: (SplashEffect) -> Unit = {}   // 👈 nuevo callback para navegar
) {
    val vm = remember { SplashFactory.create() }
    val state by vm.state.collectAsState()

    // Ejecuta una vez
    LaunchedEffect(vm) { vm.checkStatus() }

    // Escucha efectos del ViewModel y reenvíalos a AppRoot
    LaunchedEffect(vm) {
        vm.effects.collect { eff -> onEffect(eff) }
    }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val s = state) {
                SplashState.Idle, SplashState.Loading -> CircularProgressIndicator()
                is SplashState.Success -> Text("OK: ${s.status}")
                is SplashState.Error   -> Text("Error: ${s.message.orEmpty()}")
            }
        }
    }
}
