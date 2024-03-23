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

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import info.hridoydas.lifecanvas.common.ScreenDestinations
import info.hridoydas.lifecanvas.theme.LifeCanvasTheme
import info.hridoydas.lifecanvas.theme.buttonBackgroundColor
import info.hridoydas.lifecanvas.theme.buttonSecondColor
import info.hridoydas.lifecanvas.theme.indicatorColor

@Composable
fun BottomSection(
    navController: NavHostController,
    // onBoardingViewModel : OnBoardingViewModel,
    index: Int,
    onBackClicked: () -> Unit,
    onNextClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
    ) {
        // back button
        Button(
            onClick = if (index != 0) {
                onBackClicked
            } else {
                {
//                    onBoardingViewModel.setOnSkipCaseCompleted(false)
//                    navController.navigate(ScreenDestinations.ViewScreen.route){
//                        popUpTo(ScreenDestinations.HomeScreen.route) {
//                            inclusive = true
//                        }
//                    }
                }
            },
            modifier = Modifier
                .align(CenterStart)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = buttonSecondColor,
            ),
        ) {
            Text(text = "BACK")
        }

        // next button
        Button(
            onClick = if (index != 2) {
                onNextClicked
            } else {
                {
//                    onBoardingViewModel.setOnSkipCaseCompleted(false)
                    navController.navigate(ScreenDestinations.HomeScreen.route) {
                        popUpTo(ScreenDestinations.OnBoarding.route) {
                            inclusive = true
                        }
                    }
                }
            },
            modifier = Modifier
                .align(CenterEnd)
                .height(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonBackgroundColor,
            ),
        ) {
            Text(
                text = if (index != 2) {
                    "NEXT"
                } else {
                    "GET STARTED"
                },
                modifier = Modifier.padding(5.dp),
            )
        }
    }
}

@Composable
fun IndicatorSection(
    size: Int,
    index: Int,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
    ) {
        // indicators
        Indicators(size = size, index = index)
    }
}

@Composable
fun BoxScope.Indicators(
    size: Int,
    index: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.Center),
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "",
    )
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .height(4.dp)
                .width(width.value)
                .clip(CircleShape)
                .background(
                    if (isSelected) {
                        Color.White
                    } else {
                        indicatorColor
                    },
                ),
        )
    }
}

@Composable
fun OnBoardingItem(
    item: OnBoardingItem,
    size: Int,
    index: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Image(
            modifier = Modifier.size(200.dp, 200.dp),
            painter = painterResource(item.image),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.size(20.dp))

        IndicatorSection(size, index)

        Spacer(modifier = Modifier.size(20.dp))

        Text(
            modifier = Modifier.size(281.dp, 38.dp),
            text = stringResource(item.title),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.size(20.dp))

        Text(
            modifier = Modifier.size(299.dp, 48.dp),
            text = stringResource(item.text),
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun BottomScreenPreview() {
    LifeCanvasTheme {
        BottomSection(navController = rememberNavController(), index = 1, onBackClicked = { /*TODO*/ }) {
        }
    }
}
