/*
* MIT License
*
* Copyright (c) 2024 Hridoy Chandra Das
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*
*/
package info.hridoydas.lifecanvas.auth

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import info.hridoydas.lifecanvas.auth.login.LoginScreen

const val AUTH_SCREEN = "auth"

sealed class AuthScreen(val route: String) {
    // Destinations
    data object Splash : AuthScreen("${AUTH_SCREEN}/splash")

    data object Login : AuthScreen("${AUTH_SCREEN}/login")

    data object SignUp : AuthScreen("${AUTH_SCREEN}/sign_up")
}

fun NavGraphBuilder.authNavGraph(
    onAuthSuccess: () -> Unit,
    navController: NavController,
) {
    navigation(
        startDestination = AuthScreen.Splash.route,
        route = AUTH_SCREEN,
    ) {
        composable(AuthScreen.Splash.route) {
            // Splash Screen
            // SplashScreen()
            // WelcomeScreen()
            LoginScreen(
                viewModel = viewModel(),
            )
        }

        composable(AuthScreen.Login.route) {
            // LoginScreen()
        }

        composable(AuthScreen.SignUp.route) {
        }
    }
}
