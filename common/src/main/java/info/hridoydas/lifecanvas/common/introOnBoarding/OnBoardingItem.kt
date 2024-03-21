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
package info.hridoydas.lifecanvas.common.introOnBoarding

import info.hridoydas.lifecanvas.common.R

class OnBoardingItem(
    val title: Int,
    val text: Int,
    val image: Int,
) {
    companion object {
        fun get(): List<OnBoardingItem> {
            return listOf(
                OnBoardingItem(R.string.intro_one_title, R.string.intro_one_description, R.drawable.one),
                OnBoardingItem(R.string.intro_two_title, R.string.intro_two_description, R.drawable.two),
                OnBoardingItem(R.string.intro_three_title, R.string.intro_three_description, R.drawable.three),
            )
        }
    }
}
