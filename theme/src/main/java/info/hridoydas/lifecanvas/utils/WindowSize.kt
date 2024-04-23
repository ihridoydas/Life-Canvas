/*
* MIT License
*
* Copyright (c) 2022 Hridoy Chandra Das
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
package info.hridoydas.lifecanvas.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration

data class WindowSize(
    val width: WindowType,
    val height: WindowType,
)

enum class WindowType { Compact, Small }

const val SMALL_WIDTH = 300
const val SMALL_HEIGHT = 774
const val COMPACT_WIDTH = 338
const val COMPACT_HEIGHT_LAND = 320
const val COMPACT_WIDTH_LAND = 716

@Composable
fun rememberWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    val screenWidth by remember(key1 = configuration) {
        mutableStateOf(configuration.screenWidthDp)
    }
    val screenHeight by remember(key1 = configuration) {
        mutableStateOf(configuration.screenHeightDp)
    }

    return WindowSize(
        width = when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                getScreenWidthLand(screenWidth)
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                getScreenWidth(screenWidth)
            }
            else -> {
                getScreenWidth(screenWidth)
            }
        },
        height = when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                getScreenHeightLand(screenHeight)
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                getScreenHeight(screenHeight)
            }
            else -> {
                getScreenHeight(screenHeight)
            }
        },
    )
}

// ORIENTATION PORTRAIT のため
fun getScreenWidth(width: Int): WindowType =
    when {
        width > COMPACT_WIDTH -> WindowType.Compact
        else -> WindowType.Small
    }

fun getScreenHeight(height: Int): WindowType =
    when {
        height > SMALL_HEIGHT -> WindowType.Compact
        else -> WindowType.Small
    }

// ORIENTATION LANDSCAPE のため
fun getScreenWidthLand(width: Int): WindowType =
    when {
        width >= COMPACT_WIDTH_LAND -> WindowType.Compact
        else -> WindowType.Small
    }

fun getScreenHeightLand(height: Int): WindowType =
    when {
        height >= COMPACT_HEIGHT_LAND -> WindowType.Compact
        else -> WindowType.Small
    }

// 画面の幅が300dp以下の場合
fun isSmallDevice(width: Int): Boolean =
    when {
        width < SMALL_WIDTH -> true
        else -> false
    }
