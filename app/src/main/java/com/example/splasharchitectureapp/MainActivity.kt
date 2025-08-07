// ui/MainActivity.kt
package com.example.splasharchitectureapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.splasharchitectureapp.presentation.splash.SplashScreen
import com.example.splasharchitectureapp.presentation.splash.SplashViewModel
import com.example.splasharchitectureapp.domain.usecase.CheckSplashStatusUseCase
import com.example.splasharchitectureapp.data.datasource.FakeAppStatusRemoteDataSource
import com.example.splasharchitectureapp.data.repository.AppStatusRepositoryImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Creamos el ViewModel manualmente (sin Hilt aún)
        val fakeDataSource = FakeAppStatusRemoteDataSource()
        val repository = AppStatusRepositoryImpl(fakeDataSource)
        val useCase = CheckSplashStatusUseCase(repository)
        val viewModel = SplashViewModel(useCase)

        setContent {
            MaterialTheme {
                Surface {
                    SplashScreen(viewModel)
                }
            }
        }
    }
}
