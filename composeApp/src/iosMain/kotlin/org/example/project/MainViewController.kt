// composeApp/src/iosMain/kotlin/org/example/project/App.ios.kt
package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import org.example.project.presentation.splash.SplashRoot

fun MainViewController(): UIViewController =
    ComposeUIViewController { MaterialTheme { SplashRoot() } }
