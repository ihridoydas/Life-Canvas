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
package info.hridoydas.lifecanvas.auth.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import info.hridoydas.lifecanvas.components.LifeCanvasPreview
import info.hridoydas.lifecanvas.theme.LifeCanvasTheme
import info.hridoydas.lifecanvas.theme.R

@Composable
fun SplashScreenCustom(
    splashViewModel: SplashViewModel,
    navController: NavController,
    onAuthSuccess: () -> Unit,
) {
    val splashUIState = splashViewModel.uiState.collectAsStateWithLifecycle()

    when (val state = splashUIState.value) {
        SplashUIState.Authenticated -> {
            LaunchedEffect(Unit) {
                onAuthSuccess()
            }
        }
        is SplashUIState.Splash -> {
            if (state.moveLogin){
                LaunchedEffect(Unit) {
                    navController.navigate(AuthScreen.Login.route){
                        popUpTo(AuthScreen.Login.route){
                            inclusive = true
                        }
                    }
                }

            }else{
                Splash(state = state)
            }
        }
    }
}

@Composable
fun Splash(state: SplashUIState.Splash) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.anim_logo),
            contentDescription = "logo",
        )
        Spacer(modifier = Modifier.size(12.dp))
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}

@LifeCanvasPreview
@Composable
fun SplashScreenPreview() {
    LifeCanvasTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Splash(SplashUIState.Splash())
        }
    }
}
