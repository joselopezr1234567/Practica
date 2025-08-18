package org.example.project.presentation.nav

sealed interface AppScreen {
    data object Splash : AppScreen
    data object Login  : AppScreen
    data object Home   : AppScreen // placeholder
}
