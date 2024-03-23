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
package info.hridoydas.lifecanvas.utils

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val DEFAULT_DENSITY = 2.625

// Dp to sp
@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

/**
 * An sp unit that is not system scaled (font size and display size in settings)
 */
val Int.nonScaledSp
    // 2.625 is default scaling factor for medium density device
    @Composable
    get() = ((DEFAULT_DENSITY / LocalDensity.current.density) * this).sp

fun pxToDp(
    context: Context,
    px: Float,
): Dp {
    return Dp(px / context.resources.displayMetrics.density)
}

/**
 * 画面をスリープ（自動ロック）にさせないようにする
 * @param activity Activity.
 */
suspend fun keepScreenOn(activity: Activity) =
    withContext(Dispatchers.Main) {
        // CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
        // 上記のExceptionを防ぐ為Dispatchers.Mainで実行
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

/**
 * 画面をスリープ（自動ロック）にさせない状態を解除する
 * @param activity Activity.
 */
suspend fun cancelKeepScreenOn(activity: Activity) =
    withContext(Dispatchers.Main) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

fun dpToPx(
    dp: Dp,
    context: Context,
): Float {
    val metrics = context.resources.displayMetrics
    return dp.value * metrics.density
}
