// composeApp/src/commonMain/kotlin/org/example/project/presentation/login/LoginRoot.kt
package org.example.project.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation   // ✅ IMPORT CORRECTO
import org.example.project.presentation.di.AuthFactory

@Composable
fun LoginRoot(vm: LoginViewModel = remember { AuthFactory.createViewModel() }) {
    val state by vm.state.collectAsState()

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val s = state) {
                is LoginState.Editing -> {
                    OutlinedTextField(
                        value = s.email,
                        onValueChange = vm::onEmailChange,
                        label = { Text("Email") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    val visual = if (s.isPasswordVisible)
                        VisualTransformation.None              // ✅ ASÍ ES
                    else
                        PasswordVisualTransformation()          // ✅ Y ASÍ CUANDO OCULTAS

                    OutlinedTextField(
                        value = s.password,
                        onValueChange = vm::onPasswordChange,
                        label = { Text("Contraseña") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = visual,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    Button(onClick = vm::onSubmit) { Text("Iniciar sesión") }
                }
                is LoginState.Loading -> Text("Cargando…")
                is LoginState.Success -> Text("¡Bienvenido, ${s.user.name}!")
                is LoginState.Error -> {
                    Text("Error: ${s.message}")
                    Button(onClick = vm::onRetry) { Text("Reintentar") }
                }
            }
        }
    }
}

