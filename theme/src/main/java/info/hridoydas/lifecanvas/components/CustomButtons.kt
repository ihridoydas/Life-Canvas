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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import info.hridoydas.lifecanvas.theme.buttonBackgroundColor

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    size: DpSize,
    isFillMaxWidth: Boolean = false,
    onClick: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    icon: ImageVector? = null,
    shape: Shape = RoundedCornerShape(8.dp),
    showShadow: Boolean = false,
    border: BorderStroke? = null,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth(isFillMaxWidth)
                .size(size.width, size.height),
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
            shape = shape,
            elevation = if (showShadow) {
                ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                )
            } else {
                ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp,
                )
            },
            border = border,
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                )
            }
            Spacer(modifier = modifier.width(8.dp))
            Text(text = text)
        }
    }
}

private fun Modifier.fillMaxWidth(fraction: Boolean): Modifier {
    return if (fraction) {
        fillMaxWidth()
    } else {
        this
    }
}

@LifeCanvasPreview
@Composable
fun ComposeButtonPreview() {
    CustomButton(
        text = "Like",
        size = DpSize(120.dp, 40.dp),
        onClick = {},
        icon = Icons.Filled.Favorite,
        isFillMaxWidth = false,
        containerColor = buttonBackgroundColor,
        contentColor = Color.White,
        shape = CircleShape,
        showShadow = true,
    )
}
