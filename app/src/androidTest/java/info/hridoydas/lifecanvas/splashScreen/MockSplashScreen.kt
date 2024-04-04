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

import android.animation.Animator
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.window.SplashScreen
import androidx.test.core.app.ApplicationProvider

interface OnExitAnimationListener {
    fun onSplashScreenExit()
}

internal const val DURATION = 1000L

open class MockSplashScreen : SplashScreen {
    private var onExitAnimationListener: OnExitAnimationListener? = null
    private val appContext: android.content.Context = ApplicationProvider.getApplicationContext()

    override fun setOnExitAnimationListener(listener: SplashScreen.OnExitAnimationListener) {
        this.onExitAnimationListener
    }

    override fun clearOnExitAnimationListener() {
        onExitAnimationListener = null
    }

    override fun setSplashScreenTheme(themeId: Int) {
        // check if themeId is a valid resource identifier
        appContext.resources.getResourceName(themeId)

        // set the theme
        appContext.setTheme(themeId)
    }

    internal fun triggerOnSplashScreenExit() {
        onExitAnimationListener?.onSplashScreenExit()
    }

    internal fun getExitAnimationListener(): OnExitAnimationListener? {
        return onExitAnimationListener
    }

    internal fun performSplashScreenExitAnimation(
        iconView: View,
        animator: Animator,
    ) {
        iconView.animation = object : Animation() {
            override fun applyTransformation(
                interpolatedTime: Float,
                t: Transformation,
            ) {
                val scale = 1.0f - interpolatedTime
                iconView.scaleX = scale
                iconView.scaleY = scale

                if (interpolatedTime >= 1.0f) {
                    animator.end()
                    triggerOnSplashScreenExit()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }

            override fun getDuration(): Long {
                return DURATION
            }
        }

        iconView.startAnimation(iconView.animation)
    }
}
