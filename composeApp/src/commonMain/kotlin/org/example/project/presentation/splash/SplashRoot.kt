// composeApp/src/commonMain/kotlin/org/example/project/presentation/splash/SplashRoot.kt
package org.example.project.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.project.presentation.splash.di.SplashFactory

@Composable
fun SplashRoot() {
    val vm = remember { SplashFactory.create() }   // estable
    val state by vm.state.collectAsState()

    LaunchedEffect(vm) { vm.checkStatus() }        // una vez por instancia

    MaterialTheme {                                // importante en iOS
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
