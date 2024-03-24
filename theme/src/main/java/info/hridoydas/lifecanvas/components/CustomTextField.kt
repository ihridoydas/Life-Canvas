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
package info.hridoydas.lifecanvas.components

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import info.hridoydas.lifecanvas.theme.LifeCanvasTheme
import info.hridoydas.lifecanvas.theme.TEXT_FIELD_BORDER_COLOR

@Composable
fun LifeCanvasTextField(
    textFieldTitle: String,
    value: String,
    @StringRes label: Int? = null,
    hint: String = "",
    onValueChanged: (value: String) -> Unit,
    isPasswordField: Boolean = false,
    isClickOnly: Boolean = false,
    onClick: () -> Unit = {},
    @StringRes error: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onDone: () -> Unit = {},
    colors: TextFieldColors,
) {
    val focusManager = LocalFocusManager.current
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = textFieldTitle,
            style = MaterialTheme.typography.bodySmall,
            textAlign = MaterialTheme.typography.bodySmall.textAlign,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (isClickOnly) {
                            onClick()
                        }
                    },
                value = value,
                onValueChange = { onValueChanged(it) },
                singleLine = true,
                isError = error != null,
                readOnly = isClickOnly,
                enabled = !isClickOnly,
                colors = colors,
                supportingText = {
                    if (error != null) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(error),
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                },
                visualTransformation = if (isPasswordField) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
                label = {
                    if (label != null) {
                        Text(text = stringResource(label))
                    }
                },
                placeholder = { Text(text = hint) },
                trailingIcon = {
                    if (error != null) {
                        Icon(Icons.Filled.Info, "error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        onDone()
                    },
                    onNext = { focusManager.moveFocus(FocusDirection.Down) },
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction,
                ),
            )
        }
    }
}

@LifeCanvasPreview
@Composable
fun LifeCanvasTextFieldPreview() {
    LifeCanvasTheme {
        Surface {
            LifeCanvasTextField(
                textFieldTitle = "Email",
                value = "",
                hint = "yourname@domain.com",
                onValueChanged = { },
                imeAction = ImeAction.Next,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(TEXT_FIELD_BORDER_COLOR),
                    focusedBorderColor = Color(TEXT_FIELD_BORDER_COLOR),
                    errorBorderColor = MaterialTheme.colorScheme.error,
                ),
            )
        }
    }
}
