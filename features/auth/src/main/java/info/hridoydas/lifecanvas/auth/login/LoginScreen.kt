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
package info.hridoydas.lifecanvas.auth.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import info.hridoydas.lifecanvas.components.AppBar
import info.hridoydas.lifecanvas.components.CustomButton
import info.hridoydas.lifecanvas.components.LifeCanvasPreview
import info.hridoydas.lifecanvas.components.LifeCanvasTextField
import info.hridoydas.lifecanvas.theme.LifeCanvasTheme
import info.hridoydas.lifecanvas.theme.TEXT_FIELD_BORDER_COLOR
import info.hridoydas.lifecanvas.theme.TEXT_FIELD_CONTAINER_COLOR
import info.hridoydas.lifecanvas.theme.backgroundColor
import info.hridoydas.lifecanvas.theme.buttonBackgroundColor
import info.hridoydas.lifecanvas.utils.AutoSizeText

const val LC_SCREEN_TOP_SECTION_WEIGHT = 6f
const val LC_SCREEN_BOTTOM_SECTION_WEIGHT = 4f

/**
 * Login Screen
 * @param modifier Modifier
 * @return Unit
 * @sample WelcomeScreenPreview
 * @see AppBar
 * @see AutoSizeText
 * @see LifeCanvasTextField
 * @see CustomButton
 */
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Login(
        onState = uiState.value,
        onEvent = { viewModel.onEvent(it) },
    )
}

@Composable
fun Login(
    modifier: Modifier = Modifier,
    onState: LoginUIState,
    onEvent: (LoginUIEvent) -> Unit,
) {
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
                        .weight(LC_SCREEN_TOP_SECTION_WEIGHT)
                        .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    contentAlignment = Alignment.TopCenter,
                ) {
                    Column(modifier = modifier.padding(top = 5.dp)) {
                        AutoSizeText(
                            text = "Login",
                            modifier = modifier,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Start,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                        )

                        Spacer(modifier = modifier.padding(top = 30.dp))

                        LifeCanvasTextField(
                            textFieldTitle = "Email",
                            value = onState.email,
                            hint = "jondoe@gmail.com",
                            onValueChanged = {
                                onEvent(LoginUIEvent.OnEmailChanged(it))
                            },
                            imeAction = ImeAction.Next,
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledPlaceholderColor = Color(TEXT_FIELD_BORDER_COLOR),
                                focusedPlaceholderColor = Color(TEXT_FIELD_BORDER_COLOR),
                                unfocusedPlaceholderColor = Color(TEXT_FIELD_BORDER_COLOR),
                                unfocusedBorderColor = Color(TEXT_FIELD_BORDER_COLOR),
                                focusedContainerColor = Color(TEXT_FIELD_CONTAINER_COLOR),
                                unfocusedContainerColor = Color(TEXT_FIELD_CONTAINER_COLOR),
                                focusedBorderColor = Color(TEXT_FIELD_BORDER_COLOR),
                                disabledBorderColor = Color(TEXT_FIELD_BORDER_COLOR),
                                errorBorderColor = MaterialTheme.colorScheme.error,
                            ),
                        )
                        LifeCanvasTextField(
                            textFieldTitle = "Password",
                            value = onState.password,
                            hint = "Password",
                            onValueChanged = {
                                onEvent(LoginUIEvent.OnPasswordChanged(it))
                            },
                            isPasswordField = true,
                            imeAction = ImeAction.Next,
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledPlaceholderColor = Color(TEXT_FIELD_BORDER_COLOR),
                                focusedPlaceholderColor = Color(TEXT_FIELD_BORDER_COLOR),
                                unfocusedPlaceholderColor = Color(TEXT_FIELD_BORDER_COLOR),
                                unfocusedBorderColor = Color(TEXT_FIELD_BORDER_COLOR),
                                focusedContainerColor = Color(TEXT_FIELD_CONTAINER_COLOR),
                                unfocusedContainerColor = Color(TEXT_FIELD_CONTAINER_COLOR),
                                focusedBorderColor = Color(TEXT_FIELD_BORDER_COLOR),
                                errorBorderColor = MaterialTheme.colorScheme.error,
                            ),
                        )
                        Box(
                            modifier = modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.TopEnd,
                        ) {
                            AutoSizeText(
                                text = "If you forgot password? Forgot Password ",
                                modifier = modifier,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Spacer(modifier = modifier.padding(10.dp))

                        CustomButton(
                            text = "LOGIN",
                            size = DpSize(120.dp, 48.dp),
                            onClick = {
                                onEvent.invoke(LoginUIEvent.Login)
                            },
                            isFillMaxWidth = true,
                            containerColor = buttonBackgroundColor,
                            contentColor = Color.White,
                            shape = RoundedCornerShape(8.dp),
                            showShadow = true,
                        )
                    }
                }

                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .weight(LC_SCREEN_BOTTOM_SECTION_WEIGHT)
                        .padding(20.dp),
                    contentAlignment = Alignment.TopCenter,
                ) {
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            modifier = modifier,
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            HorizontalDivider(
                                modifier = Modifier.weight(1f),
                                color = Color(TEXT_FIELD_BORDER_COLOR),
                            )

                            Text(
                                text = "or",
                                color = Color(TEXT_FIELD_BORDER_COLOR),
                                modifier = modifier.padding(horizontal = 5.dp),
                            )

                            HorizontalDivider(
                                modifier = Modifier.weight(1f),
                                color = Color(TEXT_FIELD_BORDER_COLOR),
                            )
                        }
                        Spacer(modifier = modifier.padding(10.dp))

                        CustomButton(
                            text = "Login with Google",
                            size = DpSize(120.dp, 48.dp),
                            icon = Icons.Filled.AccountCircle,
                            onClick = {},
                            isFillMaxWidth = true,
                            containerColor = backgroundColor,
                            contentColor = Color.White,
                            shape = RoundedCornerShape(8.dp),
                            showShadow = true,
                            border = BorderStroke(2.dp, buttonBackgroundColor),
                        )

                        Spacer(modifier = modifier.padding(10.dp))

                        CustomButton(
                            text = "Login with Facebook",
                            icon = Icons.Filled.Face,
                            size = DpSize(120.dp, 48.dp),
                            onClick = {},
                            isFillMaxWidth = true,
                            containerColor = backgroundColor,
                            contentColor = Color.White,
                            shape = RoundedCornerShape(8.dp),
                            showShadow = true,
                            border = BorderStroke(2.dp, buttonBackgroundColor),
                        )
                        Spacer(modifier = modifier.padding(vertical = 20.dp))

                        Box(
                            modifier = modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            AutoSizeText(
                                text = "Don’t have an account? Register",
                                modifier = modifier,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                            )
                        }
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
            Login(onState = LoginUIState(), onEvent = {})
        }
    }
}
