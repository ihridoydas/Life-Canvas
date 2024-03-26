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
package info.hridoydas.lifecanvas.introOnBoarding.showCase

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned

/**
 * Creates a [IntroShowCaseState] that is remembered across compositions.
 *
 * Changes to the provided values for [initialIndex] will **not** result in the state being
 * recreated or changed in any way if it has already
 * been created.
 *
 * @param initialIndex the initial value for [IntroShowCaseState.currentTargetIndex]
 */
@Composable
fun rememberIntroShowCaseState(initialIndex: Int = 0): IntroShowCaseState {
    return remember {
        IntroShowCaseState(
            initialIndex = initialIndex,
        )
    }
}

/**
 * Modifier that marks Compose UI element as a target for [IntroShowCase]
 */
fun Modifier.introShowCaseTarget(
    state: IntroShowCaseState,
    index: Int,
    style: ShowcaseStyle = ShowcaseStyle.Default,
    content: @Composable BoxScope.() -> Unit,
): Modifier =
    onGloballyPositioned { coordinates ->
        state.targets[index] = IntroShowcaseTargets(
            index = index,
            coordinates = coordinates,
            style = style,
            content = content,
        )
    }

class IntroShowCaseState internal constructor(
    initialIndex: Int,
) {
    internal var targets = mutableStateMapOf<Int, IntroShowcaseTargets>()

    var currentTargetIndex by mutableStateOf(initialIndex)
        internal set

    val currentTarget: IntroShowcaseTargets?
        get() = targets[currentTargetIndex]
}