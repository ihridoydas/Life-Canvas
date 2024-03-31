package info.hridoydas.lifecanvas.auth.splash

sealed class SplashUIState {
    data object Authenticated : SplashUIState()
    data class Splash(
        val isLoading: Boolean = false,
        val moveLogin: Boolean = false,
    ): SplashUIState()
}
