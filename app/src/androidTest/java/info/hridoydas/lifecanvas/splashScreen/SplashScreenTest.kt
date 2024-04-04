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
package info.hridoydas.lifecanvas.splashScreen

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.core.splashscreen.SplashScreen
import androidx.test.ext.junit.runners.AndroidJUnit4
import info.hridoydas.lifecanvas.R
import info.hridoydas.lifecanvas.theme.splashScreen.SplashViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashScreenTest : MockSplashScreen() {
    private lateinit var viewModel: SplashViewModel
    private lateinit var splashScreen: MockSplashScreen

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        viewModel = SplashViewModel()
        // Create a MockSplashScreen
        splashScreen = MockSplashScreen()
    }

    @Test
    fun testSplashScreenDisplayed() {
        composeTestRule.setContent {
            SplashScreen.KeepOnScreenCondition {
                !viewModel.isLoading.value
            }
            splashScreen.setSplashScreenTheme(R.style.Theme_CustomSplashScreenTheme)
            splashScreen.setOnExitAnimationListener { splashScreen ->
                // Do something when the splash screen is dismissed
                splashScreen.iconAnimationDuration
                getExitAnimationListener()
            }
        }
    }
}
