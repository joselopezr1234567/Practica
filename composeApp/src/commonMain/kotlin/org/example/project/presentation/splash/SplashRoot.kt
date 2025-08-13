// composeApp/src/commonMain/kotlin/org/example/project/presentation/splash/SplashRoot.kt
package org.example.project.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun SplashRoot() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101820)),
        contentAlignment = Alignment.Center
    ) {
        Text("SPLASH iOS OK", color = Color(0xFFFEE715), fontSize = 28.sp)
    }
}
