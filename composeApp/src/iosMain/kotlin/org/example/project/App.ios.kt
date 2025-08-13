package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import org.example.project.presentation.splash.SplashRoot

fun MainViewController(): UIViewController = ComposeUIViewController { SplashRoot() }
