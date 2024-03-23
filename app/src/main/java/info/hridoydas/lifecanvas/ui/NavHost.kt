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
package info.hridoydas.lifecanvas.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import info.hridoydas.lifecanvas.ScreenDestinations
import info.hridoydas.lifecanvas.auth.AUTH_SCREEN
import info.hridoydas.lifecanvas.auth.authNavGraph
import info.hridoydas.lifecanvas.introOnBoarding.OnBoardingScreen
import info.hridoydas.lifecanvas.navigation.canGoBack
import info.hridoydas.lifecanvas.navigation.navigateTo
import info.hridoydas.lifecanvas.navigation.screen
import info.hridoydas.lifecanvas.screens.HomeScreen
import info.hridoydas.lifecanvas.screens.ViewScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainAnimationNavHost(
    navController: NavHostController,
    startDestination: String = AUTH_SCREEN,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        authNavGraph(
            navController = navController,
            onAuthSuccess = {
                navController.navigateTo(ScreenDestinations.HomeScreen.route)
            },
        )

        screen(ScreenDestinations.OnBoarding.route) {
            OnBoardingScreen(navController = navController)
        }

        screen(ScreenDestinations.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        screen(ScreenDestinations.ViewScreen.route) {
            ViewScreen(
                onBackPress = {
                    // navigateTo のためNavHostControllerを作成します。
                    navController.navigateTo(ScreenDestinations.HomeScreen.route)
                },
            )
        }
    }
    // Back Handler
    BackHandler {
        if (navController.canGoBack) {
            if (navController.currentBackStackEntry?.destination?.route != ScreenDestinations.HomeScreen.route) {
                navController.popBackStack()
            }
        }
    }
}
