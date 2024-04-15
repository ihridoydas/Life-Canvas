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
package info.hridoydas.lifecanvas.common.introOnBoarding.showCase

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun IntroShowCaseScaffold(
    showIntroShowCase: Boolean,
    onShowCaseCompleted: () -> Unit,
    modifier: Modifier = Modifier,
    state: IntroShowCaseState = rememberIntroShowCaseState(),
    content: @Composable IntroShowCaseScope.() -> Unit,
) {
    val scope = remember(state) {
        IntroShowCaseScope(state)
    }

    Box(modifier) {
        scope.content()

        if (showIntroShowCase) {
            IntroShowCase(
                state = state,
                onShowCaseCompleted = onShowCaseCompleted,
            )
        }
    }
}

class IntroShowCaseScope(
    private val state: IntroShowCaseState,
) {
    /**
     * Modifier that marks Compose UI element as a target for [IntroShowCase]
     */
    fun Modifier.introShowCaseTarget(
        index: Int,
        style: ShowcaseStyle = ShowcaseStyle.Default,
        content: @Composable BoxScope.() -> Unit,
    ): Modifier =
        introShowCaseTarget(
            state = state,
            index = index,
            style = style,
            content = content,
        )
}
