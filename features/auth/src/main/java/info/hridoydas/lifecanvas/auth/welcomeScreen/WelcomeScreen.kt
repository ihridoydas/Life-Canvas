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
package info.hridoydas.lifecanvas.auth.welcomeScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import info.hridoydas.lifecanvas.components.AppBar
import info.hridoydas.lifecanvas.components.CustomButton
import info.hridoydas.lifecanvas.components.LifeCanvasPreview
import info.hridoydas.lifecanvas.theme.LifeCanvasTheme
import info.hridoydas.lifecanvas.theme.backgroundColor
import info.hridoydas.lifecanvas.theme.buttonBackgroundColor
import info.hridoydas.lifecanvas.utils.AutoSizeText

const val WC_SCREEN_TOP_SECTION_WEIGHT = 0.7f
const val WC_SCREEN_BOTTOM_SECTION_WEIGHT = 0.3f

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    // Welcome Screen
    Scaffold(
        topBar = {
            AppBar(
                navIcon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            )
        },
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
            ) {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .weight(WC_SCREEN_TOP_SECTION_WEIGHT),
                    contentAlignment = Alignment.TopCenter,
                ) {
                    Column(modifier = modifier.padding(top = 66.dp)) {
                        AutoSizeText(
                            text = "Welcome to LifeCanvas",
                            modifier = modifier,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                        )

                        Spacer(modifier = modifier.padding(5.dp))

                        AutoSizeText(
                            text = "Please login to your account or create \nnew account to continue",
                            modifier = modifier.padding(top = 10.dp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }

                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .weight(WC_SCREEN_BOTTOM_SECTION_WEIGHT),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(modifier = modifier.padding(10.dp)) {
                        CustomButton(
                            text = "LOGIN",
                            size = DpSize(120.dp, 48.dp),
                            onClick = {},
                            isFillMaxWidth = true,
                            containerColor = buttonBackgroundColor,
                            contentColor = Color.White,
                            shape = RoundedCornerShape(8.dp),
                            showShadow = true,
                        )

                        Spacer(modifier = modifier.padding(10.dp))

                        CustomButton(
                            text = "CREATE ACCOUNT",
                            size = DpSize(120.dp, 48.dp),
                            onClick = {},
                            isFillMaxWidth = true,
                            containerColor = backgroundColor,
                            contentColor = Color.White,
                            shape = RoundedCornerShape(8.dp),
                            showShadow = true,
                            border = BorderStroke(2.dp, buttonBackgroundColor),
                        )
                    }
                }
            }
        },
    )
}

@LifeCanvasPreview
@Composable
fun WelcomeScreenPreview() {
    LifeCanvasTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            WelcomeScreen()
        }
    }
}
