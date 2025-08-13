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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.project.presentation.splash.di.SplashFactory

@Composable
fun SplashScreen(vm: SplashViewModel = SplashFactory.create()) {
    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) {
        vm.checkStatus()
        // Observa efectos y navega según corresponda
        // vm.effects.collect { /* navigate */ }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            SplashState.Idle, SplashState.Loading -> CircularProgressIndicator()
            is SplashState.Success -> Text("OK: ${(state as SplashState.Success).status}")
            is SplashState.Error -> Text("Error: ${(state as SplashState.Error).message ?: ""}")
        }
    }
}